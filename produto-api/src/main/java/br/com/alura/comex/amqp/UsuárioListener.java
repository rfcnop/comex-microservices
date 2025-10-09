package br.com.alura.comex.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alura.comex.dto.AutenticaçãoDto;
import br.com.alura.comex.service.AutenticacaoService;

@Component
public class UsuárioListener {

    @Autowired
    AutenticacaoService autenticacaoService;

    @RabbitListener(queues=AMQPConfiguration.QUEUE_AUTENTICACAO)
    public void recebeMensagem(AutenticaçãoDto autenticaçãoDto) {
        var autenticacao = autenticaçãoDto.toAutenticacao();
        autenticacaoService.registraAutenticação(autenticacao);
    }

}
