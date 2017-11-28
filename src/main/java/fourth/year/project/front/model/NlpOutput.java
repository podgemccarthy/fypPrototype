package fourth.year.project.front.model;

import java.util.ArrayList;
import java.util.List;

public class NlpOutput {

	
	 private final String message;
   
	private List<String> topics = new ArrayList<String>();
	private String intentName;
	private String parameters;
	private String foundAnswer;

  
	public NlpOutput(String message, List<String> topics,String intentName, String parameters, String foundAnswer) {
       this.message = message;
       this.topics = topics;
       this.intentName = intentName;
       this.parameters= parameters;
       this.foundAnswer=foundAnswer;
      
   }
	public NlpOutput(String message, List<String> topics,String intentName, String parameters) {
    this.message = message;
    this.topics = topics;
    this.intentName = intentName;
    this.parameters= parameters;
    
   
}
   public String getMessage() {
 		return message;
 	}

 	public List<String> getTopics() {
 		return topics;
 	}
	
 	
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	 public String getFoundAnswer() {
			return foundAnswer;
		}
		public void setFoundAnswer(String foundAnswer) {
			this.foundAnswer = foundAnswer;
		}

  

}
