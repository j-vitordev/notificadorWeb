package app.notificadorweb.service;

import app.notificadorweb.domain.Pedido;
import app.notificadorweb.domain.StatusPedido;
import app.notificadorweb.exception.PedidoNaoEncontradoException;
import app.notificadorweb.exception.StatusPedidoInvalidoException;
import org.springframework.stereotype.Service;
import app.notificadorweb.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private WhatsAppService whatsappService;


    public PedidoService(PedidoRepository pedidoRepository, WhatsAppService whatsappService) {
        this.pedidoRepository = pedidoRepository;
        this.whatsappService = whatsappService;

    }


    public Pedido criarPedido(Long produtoId, String nomeCliente, String telefoneCliente) {
        String nomeProduto = resolverNomeProduto(produtoId);

        String codigoRastreio = gerarCodigoRastreio();

        Pedido pedido = new Pedido(nomeProduto, codigoRastreio);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoSalvo;
    }

    public Pedido atualizarStatus(Long pedidoId, String novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId).orElseThrow(() ->
                new PedidoNaoEncontradoException(pedidoId));

        StatusPedido statusConvertido;

        try {
            statusConvertido = StatusPedido.valueOf(novoStatus);
        } catch (IllegalArgumentException e) {
            throw new StatusPedidoInvalidoException(novoStatus);
        }

        pedido.atualizarStatus(statusConvertido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        whatsappService.enviarMensagem("Vitor", "85900001111", pedidoSalvo.getId(), pedidoSalvo.getStatus());

        return pedidoSalvo;
    }


    private String resolverNomeProduto(Long produtoId) {
        if (produtoId == 1L) {
            return "Moto G";
        }
        if (produtoId == 2L) {
            return "Samsung Galaxi";
        }
        if (produtoId == 3L) {
            return "Redmi Note 15";
        }

        return "Produto desconhecido";
    }

    private String gerarCodigoRastreio() {
        return "RAST-" + System.currentTimeMillis();
    }
}
