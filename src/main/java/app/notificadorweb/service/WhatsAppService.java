package app.notificadorweb.service;

import app.notificadorweb.domain.StatusPedido;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {
    public void enviarMensagem(String nomeCliente, String telefoneCliente, Long pedidoId, StatusPedido status) {

        System.out.println("Whatsapp \n\nPara: " + nomeCliente + "(" + telefoneCliente + ") \n\nPedido " +
        pedidoId + "\n\nStatus: " + status);

    }


}
