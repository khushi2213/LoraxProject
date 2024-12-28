package com.jsonautomation;

import org.junit.jupiter.api.Test;

import java.net.URI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;


public class SqsTest {

    private WebDriver driver;
    private SqsClient sqsClient;
    private String queueUrl;

    @BeforeClass
    public void setUp() {
        // Set up WebDriver
        //System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();

        // Set up SQS client
        sqsClient = SqsClient.builder()
                .endpointOverride(URI.create("http://localhost:4566/"))
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")
                ))
                .build();
        queueUrl = sqsClient.getQueueUrl(request -> request.queueName("MyQueue")).queueUrl();
    }

    @Test
    public void testSQSMessaging() {
        // Example Selenium test (replace with actual test steps)
        driver.get(queueUrl);

        // Send a message to SQS
        String messageBody = "Hello from Selenium SQS Test!";
        sqsClient.sendMessage(SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .build());

        // Receive and validate the message
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build();
        String receivedMessage = sqsClient.receiveMessage(receiveRequest).messages().get(0).body();
        assert messageBody.equals(receivedMessage) : "Received message does not match the sent message!";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (sqsClient != null) {
            sqsClient.close();
        }
    }
}