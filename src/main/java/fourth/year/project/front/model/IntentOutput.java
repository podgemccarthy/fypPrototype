package fourth.year.project.front.model;

import java.util.HashMap;

import com.google.gson.JsonElement;

import javassist.compiler.ast.NewExpr;

public class IntentOutput {

	public IntentOutput(String intentName, HashMap<String,JsonElement> parameters) {
		this.intentName = intentName;
		this.parameters = parameters;
		
	}
	private String intentName;
	private HashMap<String,JsonElement> parameters = new HashMap<>();
	
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public HashMap<String, JsonElement> getParameters() {
		return parameters;
	}
	public void setParameters(HashMap<String, JsonElement> parameters) {
		this.parameters = parameters;
	}

}
