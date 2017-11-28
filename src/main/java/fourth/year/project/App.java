package fourth.year.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.messenger4j.MessengerPlatform;
import com.github.messenger4j.send.MessengerSendClient;

import fourth.year.project.App;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {
	public static final String PAGE_ACCESS_TOKEN = "EAATeaA5mq1IBAKg7aZB2p7z4uyw076JPLy3ep80H92Ru96VTe6SBNOAWdtUGNote4QnpWsMeUZC21TgAWUSTcRbS1tSyOtgZA1EQ6w43gIK8vwrHUyz2lTfWZCKkZB4ssRJvwJrbTfjqrf3yV2jnBZBXOp3kZC1Xm8OivtWyKVFHQZDZD";

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(App.class);
}
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(App.class, args);

	}
	
	@Bean
	public MessengerSendClient messengerSendClient(@Value(PAGE_ACCESS_TOKEN) String pageAccessToken) {
		return MessengerPlatform.newSendClientBuilder(pageAccessToken).build();
	}
	

}
