package org.zerock.wcup;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@SpringBootTest

public class FCMSendTest {


    @Autowired
    FirebaseMessaging firebaseMessaging;

    @Autowired
    GoogleCredentials googleCredentials;

    @Test
    public void test1()throws Exception{

        googleCredentials.refreshIfExpired();
        System.out.println(googleCredentials.getAccessToken().getTokenValue());

    }



    @Test
    public void testSend1() throws Exception{

        System.out.println(firebaseMessaging);

        String registrationToken = "ctYPhp-CRHRqxiM7hO4UC1:APA91bGhmfNIjhxyeHJviRRR_IDlmuFjTdtLP8MV5vgZWUMe8N20Qe6sxSXbr6NqedfPhVfsEnWIlXmVQ1qnZrTzwc83mExiIr57kSoM0sJXNvfTGuzK_41osa-eoswtbFh0-xCvhFb5";

        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle("Boot Test Title").setBody("Boot Test Body").build())
                .setToken(registrationToken)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);


    }

}
