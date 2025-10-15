package br.com.alura.comex.service;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.alura.comex.util.JsonSerializer;

@Service
public class KafkaProducerService<T> implements Closeable {
    
    private final KafkaProducer<String, T> kafkaProducer;

    private KafkaProducerService(@Value("${kafka.broker.host-and-port}") String brokerHostAndPort) {
        var propriedades = new Properties();
        propriedades.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerHostAndPort);
        propriedades.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propriedades.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        this.kafkaProducer = new KafkaProducer<String, T>(propriedades);
    }

    public void enviar(String tópico, String chave, T objetoASerSerializado) throws ExecutionException, InterruptedException {
        var record = new ProducerRecord<>(tópico, chave, objetoASerSerializado);
        Callback callback = (metadados, exceção) -> {
            if (exceção != null)
                exceção.printStackTrace();
        };
        var future = kafkaProducer.send(record, callback);
        future.get();
    }

    @Override
    public void close() throws IOException {
        kafkaProducer.close();
    }
}
