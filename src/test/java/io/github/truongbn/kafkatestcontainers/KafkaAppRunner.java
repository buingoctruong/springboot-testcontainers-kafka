package io.github.truongbn.kafkatestcontainers;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import io.github.truongbn.kafkatestcontainers.config.LocalKafkaInitializer;

@SpringBootTest
@ComponentScan(basePackages = "io.github.truongbn.kafkatestcontainers")
@ConfigurationPropertiesScan(basePackages = "io.github.truongbn.kafkatestcontainers")
class KafkaAppRunner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(KafkaAppRunner.class).initializers(new LocalKafkaInitializer())
                .run(args);
    }
}
