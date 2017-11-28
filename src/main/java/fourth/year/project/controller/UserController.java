package fourth.year.project.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fourth.year.project.data.model.Timetable;
import fourth.year.project.data.model.User;
import fourth.year.project.data.service.ElasticSearch;
import fourth.year.project.data.service.UserService;
import fourth.year.project.dialogflow.connection.DialogFlow;
import fourth.year.project.front.model.IntentOutput;
import fourth.year.project.front.model.NlpOutput;
import fourth.year.project.queryengine.PartOfSpeechTagger;
import fourth.year.project.queryengine.TopicExtraction;

@RestController
public class UserController {

	@Autowired(required = true)
	private UserService userService;
	@Autowired
	private ElasticSearch elasticSearch;
	
	@RequestMapping("/nlpMessage")
  public NlpOutput greeting(@RequestParam(value="messageContent", defaultValue="hello world") String messageContent) {
		String tags = "";
		PartOfSpeechTagger tagger = new PartOfSpeechTagger();
		String taggedMessage = tagger.tagMessage(messageContent);
		TopicExtraction topicExtraction = new TopicExtraction();
		List<String> topics = topicExtraction.extractTopics(taggedMessage);
		for(String e : topics){
			tags +=e+" ";
		}
		System.out.println(tags);
		
		String elasticAnswer = "";
//		elasticSearch.init();
//		elasticAnswer = elasticSearch.getDocumentByQuestion(tags);
		
		IntentOutput intent = new DialogFlow().InputQuery(messageContent);
		
		String intentName = "";
		
		if(!intent.getIntentName().isEmpty()){
		intentName=intent.getIntentName();
		}
		String parameters = "";
		if(intent.getParameters().containsKey("Day")){
			parameters = intent.getParameters().get("Day").getAsString();
			System.out.println("Intent has parameters Day = " + parameters );
		}
		return new NlpOutput(messageContent,topics,intentName,parameters);
  }

	
	@RequestMapping("/tagging")
  public String tagMessage(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		PartOfSpeechTagger tagger = new PartOfSpeechTagger();
		String taggedMessage = tagger.tagMessage(name);
		TopicExtraction topicExtraction = new TopicExtraction();
		List<String> topics = topicExtraction.extractTopics(taggedMessage);
		System.out.println("topic string length = " + topics.size());
		String topicString = "";
		for (String e : topics) {
			System.out.println(e);
			topicString += e + " ";
		}
      model.addAttribute("topic", topicString);
      model.addAttribute("queryString", name);
      return "greeting";
  }
	
	
//	
//	@RequestMapping("/users")
//	public List<User> getUsers() {
//		return userService.findAllUsers();
//	}
//	@GetMapping("/users/{userId}")
//	public String getUserById(@PathVariable Integer userId) {
//		Set<Timetable> time = userService.getUserById(userId).getUserTimetable();
//		return "You have " + time.iterator().next().getModule().getModuleTitle() + " on " + time.iterator().next().getDay() + " at " + time.iterator().next().getTimeStart() + " in " +time.iterator().next().getLocation();
//	}
//	
//	@RequestMapping(value = "/user/tagMessage/tagged")
//	public String getTaggedMessageContent(Model model, @RequestParam("messageContent") String messageContent){
//	
//		System.out.println("The message Content = " + messageContent);
////		PartOfSpeechTagger tagger = new PartOfSpeechTagger();
////		String taggedMessage = tagger.tagMessage(messageContent);
////		TopicExtraction topicExtraction = new TopicExtraction();
////		List<String> topics = topicExtraction.extractTopics(taggedMessage);
////		System.out.println("topic string length = " + topics.size());
////		String topicString = "";
////		for(String e : topics){
////			System.out.println(e);
////			topicString += e+ " ";
////		}
////		API api = new API();
////		System.out.println("About to check DialogFLow");
////		api.InputQuery(messageContent);
//		model.addAttribute("topics", messageContent);
//		return "topics";
//	}
	

}
