package com.aca.sample.artemis.server;

import com.aca.sample.artemis.server.config.TestQueueJmsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jms.core.JmsTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Import(TestQueueJmsConfig.class)
@SpringBootTest
public class LocalQueueTest {

    @Autowired
    @Qualifier(value = "testJmsCountDownLatch")
    private CountDownLatch testJmsCountDownLatch;

    @Autowired
    private JmsTemplate template;

    /**
     * Send a test message via JmsTemplate and check if the message is received at JmsListener within 5s
     */
    @Test
    public void testJmsMessage() throws InterruptedException {
        // Send test message
        template.convertAndSend(TestQueueJmsConfig.testQueue, "Self MQ Test Success");
        // TestJmsConsumer is defined in TestQueueJmsConfig
        assertTrue(testJmsCountDownLatch.await(5, TimeUnit.SECONDS), "MQ message not received");
        // Message received at TestJmsConsumer, log should show "Self MQ Test Success" message
    }

}
