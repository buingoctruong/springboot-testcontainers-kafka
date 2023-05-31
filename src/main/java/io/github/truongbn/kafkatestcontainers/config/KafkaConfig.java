package io.github.truongbn.kafkatestcontainers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * In this project, we are utilizing the SASL_PLAINTEXT authentication protocol.
 * To configure the required JAAS configuration strings, we should store them in a file and provide
 * the file path as the value for system properties, similar to what we are doing in the
 * kafkaTemplate Bean.
 * However, in this project, dummy values have been set for these properties.
 */
@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, String> producerFactory(KafkaProperties kafkaProperties) {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.kafkaConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(KafkaProperties kafkaProperties,
            ProducerFactory<String, String> producerFactory) {
        System.setProperty("java.security.krb5.conf", kafkaProperties.getKrb5Conf());
        System.setProperty("java,security,auth.login.config", kafkaProperties.getLoginConf());
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty("sun.security.krb5.debug", kafkaProperties.getKrb5Debug());
        return new KafkaTemplate<>(producerFactory);
    }
}
