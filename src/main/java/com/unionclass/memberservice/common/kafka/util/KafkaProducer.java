package com.unionclass.memberservice.common.kafka.util;

import com.unionclass.memberservice.common.kafka.event.MemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${spring.kafka.topics.member-created}")
    private String memberCreatedTopic;

    private final KafkaTemplate<String, MemberCreatedEvent> memberCreatedEventKafkaTemplate;

    public void sendMemberCreatedEvent(MemberCreatedEvent memberCreatedEvent) {

        log.info("Kafka 메시지 전송 시작: {}", memberCreatedEvent);

        CompletableFuture<SendResult<String, MemberCreatedEvent>> future
                = memberCreatedEventKafkaTemplate.send(memberCreatedTopic, memberCreatedEvent);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Kafka 메시지 전송 실패: {}", memberCreatedEvent, ex);
            } else {
                log.info("Kafka 메시지 전송 성공: offset={}, topic={}",
                        result.getRecordMetadata().offset(), result.getRecordMetadata().topic());
            }
        });
    }
}