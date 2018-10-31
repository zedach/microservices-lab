package io.debezium.examples.ticketmsa.invoice;

import org.aerogear.kafka.cdi.annotation.Consumer;
import org.aerogear.kafka.cdi.annotation.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@KafkaConfig(bootstrapServers = "#{KAFKA_SERVICE_HOST}:#{KAFKA_SERVICE_PORT}")
public class InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    @Consumer(topics = "#{ORDER_TOPIC_NAME}", groupId = "InvoiceService")
    public void orderArrived(final String order) {
        LOGGER.info("Order event '{}' arrived", order);
    }


}
