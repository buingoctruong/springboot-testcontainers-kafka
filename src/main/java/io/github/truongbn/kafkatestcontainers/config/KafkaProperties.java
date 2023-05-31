package io.github.truongbn.kafkatestcontainers.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Validated
@ConfigurationProperties(prefix = "kafka.config")
public class KafkaProperties {
    private static final String SECURITY_PROTOCOL = "security.protocol";
    private static final int DEFAULT_RECONNECT_BACKOFF = 500;
    private static final String KAFKA_KRB5_CONF = "kafka.krb5.conf";
    private static final String KAFKA_KRB5_DEBUG = "kafka.krb5.debug";
    private static final String KAFKA_LOGIN_CONFIG = "kafka.login.config";
    @NotEmpty
    private final String bootstrapServer;
    private final int maxBlockMs;
    private final int maxRequestSize;
    private final int retry;
    private final long bufferMemory;
    private final int batchSize;
    private final int lingerMs;
    private final int metadataMaxAge;
    private final String acks;
    @NotEmpty
    private final String krb5Conf;
    @NotEmpty
    private final String krb5Debug;
    @NotEmpty
    private final String loginConf;
    @NotEmpty
    private final String securityProtocol;
    public Map<String, Object> kafkaConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, maxRequestSize);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.RETRIES_CONFIG, retry);
        props.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, DEFAULT_RECONNECT_BACKOFF);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        props.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, metadataMaxAge);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(SECURITY_PROTOCOL, securityProtocol);
        props.put(KAFKA_KRB5_CONF, krb5Conf);
        props.put(KAFKA_KRB5_DEBUG, krb5Debug);
        props.put(KAFKA_LOGIN_CONFIG, loginConf);
        return props;
    }
}