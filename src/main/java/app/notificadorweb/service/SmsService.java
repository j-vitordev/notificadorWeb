package app.notificadorweb.service;

import app.notificadorweb.domain.StatusPedido;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.sms.from}")
    private String from;

    public void enviarSms(
            String nomeCliente,
            String telefoneCliente,
            Long pedidoId,
            String nomeProduto,
            StatusPedido status
    ) {

        String mensagem = """
                Olá %s!
                
                Seu pedido #%d (%s) foi atualizado.
                Novo status: %s
                
                Obrigado pela preferência!
                """.formatted(nomeCliente, pedidoId, nomeProduto, status);

        Message.creator(
                new PhoneNumber(telefoneCliente),
                new PhoneNumber(from),
                mensagem
        ).create();
    }
}
