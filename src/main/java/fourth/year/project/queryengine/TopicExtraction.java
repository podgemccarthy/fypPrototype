package fourth.year.project.queryengine;

import java.util.ArrayList;
import java.util.List;

public class TopicExtraction {

	public TopicExtraction() {
		// TODO Auto-generated constructor stub
	}

	public List<String> extractTopics(String taggedText) {
		String[] words = taggedText.split(" ");
		List<String> topics = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {

			// adjective
			if (words[i].substring(words[i].lastIndexOf("_") + 1).startsWith("J")) {
				topics.add(words[i].split("_")[0] + " ");
			}
			//ing verbs
			if (words[i].substring(words[i].lastIndexOf("_") + 1).startsWith("VBG")) {
				topics.add(words[i].split("_")[0] + " ");
			}
			// gets all nouns from sentence if the tag starts with N its a
			// noun
			if (words[i].substring(words[i].lastIndexOf("_") + 1).startsWith("N")) {
				topics.add(words[i].split("_")[0] + " ");
			}

			// pulls the verb words out
//			 if (words[i].substring(words[i].lastIndexOf("_") +1).startsWith("V")) {
//			 topics.add(words[i].split("_")[0] + " ");
//			 }

		}
		return topics;
	}
}
