package io.github.truongbn.kafkatestcontainers.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/kafka/topic")
@RequiredArgsConstructor
public class KafkaController {
    private static final String TOPIC = "kafka_topic";
    private final KafkaTemplate<String, String> kafkaTemplate;
    @GetMapping(path = "/{message}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@PathVariable("message") String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, "key",
                message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message=[" + message + "] with offset=["
                        + result.getRecordMetadata().offset() + "]");
            } else {
                log.warn("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
        });
        return new ResponseEntity<>(
                "Finish sending [" + message + "], Check log to know the result", HttpStatus.OK);
    }
}
