package br.com.alura.comex.amqp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class AMQPConfiguration {

    private static final String EXCHANGE_AUTENTICACAO = "autenticacao.exchange";
    private static final String EXCHANGE_AUTENTICACAO_DLQ = "autenticacao.exchange.dlq";
    private static final String ROUTING_KEY_AUTENTICACAO = "autenticacao";
    private static final String ROUTING_KEY_AUTENTICACAO_DLQ = "autenticacao.dlq";
    public static final String QUEUE_AUTENTICACAO = "autenticacao.queue";
    public static final String QUEUE_AUTENTICACAO_DLQ = "autenticacao.queue.dlq";
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange exchange() {
          return ExchangeBuilder.topicExchange(EXCHANGE_AUTENTICACAO).durable(true).build();
    }

    @Bean
    public TopicExchange exchangeDlq() {
          return ExchangeBuilder.topicExchange(EXCHANGE_AUTENTICACAO_DLQ).durable(true).build();
    }

    @Bean
    public Queue queueAutenticacao() {
        return QueueBuilder.durable(QUEUE_AUTENTICACAO)
            .deadLetterExchange(EXCHANGE_AUTENTICACAO_DLQ)
            .deadLetterRoutingKey(ROUTING_KEY_AUTENTICACAO_DLQ)
            .build();
    }

    @Bean
    public Queue queueAutenticacaoDlq() {
        return new Queue(QUEUE_AUTENTICACAO_DLQ, true);
    }   

    @Bean
    public Binding bindingQueueAutenticacao(TopicExchange exchange, Queue queueAutenticacao) {
        return BindingBuilder.bind(queueAutenticacao)
            .to(exchange)
            .with(ROUTING_KEY_AUTENTICACAO);
    }

    @Bean
    public Binding bindingQueueAutenticacaoDlq(TopicExchange exchangeDlq, Queue queueAutenticacaoDlq) {
        return BindingBuilder.bind(queueAutenticacaoDlq)
            .to(exchangeDlq)
            .with(ROUTING_KEY_AUTENTICACAO_DLQ);
    }
}
