package com.example.konsument.conf;

import lombok.extern.log4j.Log4j2;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

@Configuration
@Log4j2
public class ArtemisClusterConfig {

    @Value("${app.artemis.broker-url}")
    private String brokerUrl;

    @Value("${app.artemis.user}")
    private String user;

    @Value("${app.artemis.password}")
    private String password;

    @Value("${app.artemis.client-id}")
    private String clientId;


    @Bean
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        factory.setUser(user);
        factory.setPassword(password);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> artemisQueueConnectionFactory(ConnectionFactory connectionFactory,
                                                               DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-1");
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    // https://stackoverflow.com/questions/30291067/jmslistener-usage-for-publish-subscribe-topic
    public JmsListenerContainerFactory<?> artemisTopicConnectionFactory(ConnectionFactory connectionFactory,
                                                                            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}
