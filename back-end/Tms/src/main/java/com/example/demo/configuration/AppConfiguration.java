//package com.example.demo.configuration;
//
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.mongodb.event.ConnectionPoolListener;
//
//@Configuration
//public class AppConfiguration implements ConnectionPoolListener {
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//
//				registry
//				.addMapping("/*")
//						.allowedOriginPatterns("*")
////						.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
////						.allowedHeaders("*")
//						.allowCredentials(true);
//			}
//		};
//	}
//	
//	@Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//	@Bean
//	public MongoClientSettingsBuilderCustomizer customizer() {
//		return (builder) -> builder.applyToConnectionPoolSettings(connectionPool -> {
//			connectionPool.maxSize(100);
//			connectionPool.minSize(10);
//			connectionPool.maxConnectionIdleTime(5, TimeUnit.MINUTES);
//			connectionPool.maxWaitTime(2, TimeUnit.MINUTES);
//			connectionPool.maxConnectionLifeTime(30, TimeUnit.MINUTES);
//			connectionPool.addConnectionPoolListener(this);
//		});
//	}
//
//}
package com.example.demo.configuration;

