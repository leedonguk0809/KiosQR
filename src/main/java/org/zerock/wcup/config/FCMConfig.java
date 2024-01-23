package org.zerock.wcup.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.util.Arrays;

@Configuration
public class FCMConfig {


    @Value("classpath:f2024-2fcad.json")
    Resource resourceFile;

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    public FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }

    @Bean
    GoogleCredentials googleCredentials() throws Exception{
        InputStream fin = resourceFile.getInputStream();

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(fin)
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));

        googleCredentials.refreshIfExpired();

        return googleCredentials;
    }
}
