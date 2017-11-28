package fourth.year.project.data.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
@Service
public class ElasticSearch {

	public static String indexName = "faq";
	public static String typeName = "question";
	public TransportClient client;
	
	public ElasticSearch() {
		
	}

	
	public TransportClient getClient() {
		try {
			Settings esSettings = Settings.builder().put("cluster.name", "fypChatbot").build();
			System.out.println("Trying to initialize client");
			client = new PreBuiltTransportClient(esSettings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		} catch (UnknownHostException e) {
			System.out.println("THE TRANSPORT CLIENT COULDNT CONNECT");
			e.printStackTrace();
			
		}
		return client;
	}
	public void init(){
		client = getClient();
	}

	
	public String getDocumentByQuestion(String question) {
		String include[] = { "answer"};
		String exclude[] = { "" };
		String answer = "";

		
		float minScore = 0.1f;
			SearchResponse response = client.prepareSearch(indexName).setTypes(typeName)
					.setSearchType(SearchType.DEFAULT)
					.setMinScore(minScore)
					.setQuery(QueryBuilders.boolQuery()
					.must(QueryBuilders.matchQuery("question", question))
					.should(QueryBuilders.termQuery("tags", question))
					.should(QueryBuilders.matchQuery("answer", question)))
					.setFetchSource(include, exclude)
					.setFrom(0).setSize(60).setExplain(true)
					.execute()
					.actionGet();
			
			SearchHit[] results = response.getHits().getHits();

			System.out.println("yoyoyoy  " + response.toString());

			for (SearchHit hit : results) {
				
				String sourceDoc = hit.getSourceAsString();
				
				

				JSONObject json = new JSONObject(sourceDoc);
				
				 answer = json.get("answer").toString();
				 System.out.println("The answer from elasticSearch is "+answer);
				
				}
			
		if (answer.equals("")|| answer == null) {
			return null;
		}

		return answer;
	}
}
