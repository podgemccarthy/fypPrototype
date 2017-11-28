package fourth.year.project.dialogflow.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonElement;

import fourth.year.project.dialogflow.library.AIConfiguration;
import fourth.year.project.dialogflow.library.AIDataService;
import fourth.year.project.dialogflow.library.AIServiceException;
import fourth.year.project.dialogflow.library.model.AIRequest;
import fourth.year.project.dialogflow.library.model.AIResponse;
import fourth.year.project.front.model.IntentOutput;

public class DialogFlow {

	AIDataService dataService;
	public AIResponse response;
	public String entity;
	public String query;
	public String ClientAccessKey = "8ad8b4e6abca4ad496223247d137b14e";

	public DialogFlow() {

		AIConfiguration configuration = new AIConfiguration(ClientAccessKey);
		dataService = new AIDataService(configuration);
	}

	public IntentOutput InputQuery(String query) {
		String intent = "";
		AIRequest request = new AIRequest(query);
		try {
			response = dataService.request(request);

		} catch (AIServiceException e) {
			e.printStackTrace();
		}
		if (response.getStatus().getCode() == 200 && !response.getResult().getAction().contains("smalltalk")) {
			intent = response.getResult().getMetadata().getIntentName();
			System.out.println("INPUT: " + query);
			System.out.println("INTENT: " + intent);
			System.out.println("ENTITIES: " + response.getResult().getParameters());

		} else {
			System.err.println(response.getStatus().getErrorDetails());
		}

		return new IntentOutput(intent, response.getResult().getParameters());
	}

	public String ReturnExpertAnswer() {
		return query;
	}

	public String GetOfficeParams() {
		return response.getResult().getParameters().get("OfficeInformation").getAsString();
	}

	public HashMap<String, JsonElement> GetParams() {
		return response.getResult().getParameters();
	}

	public String GetEntities() {
		try {
			System.out.println(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	public String GetAPIReply() {
		return response.getResult().getFulfillment().getSpeech();
	}

	/*
	 * This is for when the API key is not specified if ever taking off server for whatever reason must specify the API key in program arguments. It is available on the API.ai site
	 */
	private static void showHelp(String errorMessage, int exitCode) {
		if (errorMessage != null && errorMessage.length() > 0) {
			System.err.println(errorMessage);
			System.err.println();
		}

		System.out.println("Usage: APIKEY");
		System.out.println();
		System.out.println("APIKEY  Your unique application key");
		System.out.println("        See https://docs.api.ai/docs/key-concepts for details");
		System.out.println();
		System.exit(exitCode);
	}
}
