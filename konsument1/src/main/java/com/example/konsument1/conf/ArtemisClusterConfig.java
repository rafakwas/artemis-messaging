package com.example.konsument1.conf;

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
    public JmsListenerContainerFactory<?> artemisTopicConnectionFactory(ConnectionFactory connectionFactory,
                                                                            DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setClientId(clientId);
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

}
