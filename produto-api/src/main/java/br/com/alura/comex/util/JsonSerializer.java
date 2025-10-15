package br.com.alura.comex.util;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String tópico, T dados) {        
        if (dados == null)
            return null;

        try {
            return objectMapper.writeValueAsBytes(dados);    
        }
        catch (JsonProcessingException exceção) {
            throw new RuntimeException("Erro de serialização", exceção);
        }
    }
    
}
