package com.snack.order.kitchen_api.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.order.kitchen_api.record.OrderRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Service
public class ReactiveKafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(ReactiveKafkaConsumerService.class);
    private final ObjectMapper objectMapper;

    // Criando um sink para armazenar mensagens recebidas de forma reativa
    private final Sinks.Many<OrderRecord> sink;

    public ReactiveKafkaConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.sink = Sinks.many().replay().latest(); // Armazena apenas a última mensagem
    }

    @KafkaListener(topics = "snack-order", groupId = "snack-group")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            // Convertendo JSON para OrderRecord
            OrderRecord order = objectMapper.readValue(record.value(), OrderRecord.class);
            logger.info("Mensagem recebida do Kafka: {}", order);

            // Publicando a mensagem no Flux de forma reativa
            sink.tryEmitNext(order);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem do Kafka", e);
        }
    }

    // Expor um Flux para ser consumido reativamente em outra parte do sistema
    public Flux<OrderRecord> getOrderStream() {
        return sink.asFlux();
    }
}
