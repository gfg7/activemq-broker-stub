package com.aca.sample.artemis.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

@TestConfiguration
@Slf4j
public class TestQueueJmsConfig {

    public static final String testQueue = "LOCAL_TEST";

    @Bean(name = "testJmsCountDownLatch")
    CountDownLatch testJmsCountDownLatch() { return new CountDownLatch(1); }

    @Bean
    public TestJmsConsumer testJmsConsumer() {
        return new TestJmsConsumer();
    }

    public static class TestJmsConsumer {
        @Autowired
        @Qualifier(value = "testJmsCountDownLatch")
        private CountDownLatch testJmsCountDownLatch;

        @JmsListener(destination = testQueue)
        public void receive(String message) {
            log.info("Received message From {} = '{}'", testQueue, message);
            testJmsCountDownLatch.countDown();
        }
    }

}
