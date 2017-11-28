package fourth.year.project.queryengine;

import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PartOfSpeechTagger {

	
	public static MaxentTagger tagger = init();

	public static MaxentTagger init() {
		try { 
			String localTagger = "src/main/resources/bidirectional-distsim-wsj-0-18.tagger";
			String serverTagger = "/home/podge/fyp/bidirectional-distsim-wsj-0-18.tagger";
			return new MaxentTagger(serverTagger);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	public PartOfSpeechTagger() {
		// TODO Auto-generated constructor stub
	}
	public String tagMessage (String messageContent){
		String tagPrep = messageContent.replaceAll("\\W", " ");
		
		String tagged = tagger.tagTokenizedString(tagPrep);
	
		System.out.println(tagged);
		return tagged;
	}
	
	
}
