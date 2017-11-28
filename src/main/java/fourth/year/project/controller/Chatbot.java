package fourth.year.project.controller;

import static com.github.messenger4j.MessengerPlatform.CHALLENGE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.MessengerPlatform.MODE_REQUEST_PARAM_NAME;
import static com.github.messenger4j.MessengerPlatform.SIGNATURE_HEADER_NAME;
import static com.github.messenger4j.MessengerPlatform.VERIFY_TOKEN_REQUEST_PARAM_NAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.receive.MessengerReceiveClient;
import com.github.messenger4j.send.MessengerSendClient;
import com.github.messenger4j.send.NotificationType;
import com.github.messenger4j.send.Recipient;

import fourth.year.project.dialogflow.connection.DialogFlow;
import fourth.year.project.front.model.IntentOutput;
import fourth.year.project.queryengine.PartOfSpeechTagger;
import fourth.year.project.queryengine.TopicExtraction;

import com.github.messenger4j.exceptions.MessengerApiException;
import com.github.messenger4j.exceptions.MessengerIOException;
import com.github.messenger4j.exceptions.MessengerVerificationException;

import com.github.messenger4j.receive.handlers.TextMessageEventHandler;

@RestController
@RequestMapping("/callback")
public class Chatbot {

	private static final String APPSECRET = "ASKAUTHORFORTHIS";
	private static final String VERIFYTOKEN = "ASKAUTHORFORTHIS";
	private final MessengerReceiveClient receiveClient;
	private final MessengerSendClient sendClient;

	
	@Autowired
	public Chatbot(@Value(APPSECRET) final String appSecret, @Value(VERIFYTOKEN) final String verifyToken, final MessengerSendClient sendClient) {

		this.receiveClient = MessengerPlatform.newReceiveClientBuilder(appSecret, verifyToken).onTextMessageEvent(newTextMessageEventHandler()).build();
		this.sendClient = sendClient;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode, @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
			@RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {

		System.out.println("Received webhook request");
		try {
			return ResponseEntity.ok(this.receiveClient.verifyWebhook(mode, verifyToken, challenge));
		} catch (MessengerVerificationException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> handleCallback(@RequestBody final String payload, @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {

		try {
			this.receiveClient.processCallbackPayload(payload, signature);

			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (MessengerVerificationException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	private TextMessageEventHandler newTextMessageEventHandler() {
		return event -> {

			final String messageId = event.getMid();
			final String messageText = event.getText();
			final String senderId = event.getSender().getId();

			PartOfSpeechTagger tagger = new PartOfSpeechTagger();
			String taggedMessage = tagger.tagMessage(messageText);

			TopicExtraction topicExtraction = new TopicExtraction();
			List<String> topics = topicExtraction.extractTopics(taggedMessage);
			System.out.println("There were " + topics.size() + " topics pulled from the message " + messageText);
			String tags = "";

			for (String e : topics) {
				tags += e + ",";
			}

			System.out.println("These are " + tags);

			String genericMessage = "Hello User!! I have received your message \'" + messageText + "\'";

			sendTextMessage(senderId, genericMessage);

			IntentOutput intent = new DialogFlow().InputQuery(messageText);

			String intentName = "";
			if (!intent.getIntentName().isEmpty()) {
				System.out.println("The intent String is not empty");
				intentName = intent.getIntentName();
			}
			String parameters = "";
			if (intent.getParameters().containsKey("Day")) {
				parameters = intent.getParameters().get("Day").getAsString();
				System.out.println("Intent has parameters Day = " + parameters);
			}

			String nlpMessage = "From this I have extracted " + topics.size() + " topics.";
			if (topics.size() > 0) {
				nlpMessage += "These topics are \'" + tags + "/'.";
			}
			if (!intentName.equals("")) {
				nlpMessage += "I have also extracted the intent \'" + intentName + "\'.";
			} else {
				nlpMessage += "Unfortunatley there was no intent or entities extracted from this message.";
			}
			if (!parameters.equals("")) {
				nlpMessage += " and the entity " + parameters;
			}
			sendTextMessage(senderId, nlpMessage);
		};
	}

	private void sendTextMessage(String recipientId, String text) {
		try {
			final Recipient recipient = Recipient.newBuilder().recipientId(recipientId).build();
			final NotificationType notificationType = NotificationType.REGULAR;
			final String metadata = "DEVELOPER_DEFINED_METADATA";

			this.sendClient.sendTextMessage(recipient, notificationType, text, metadata);
		} catch (MessengerApiException | MessengerIOException e) {
			System.out.println("Message copuld not be sent");
		}
	}

}
