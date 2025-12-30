package app.notificadorweb.service;

import app.notificadorweb.domain.Pedido;
import app.notificadorweb.domain.StatusPedido;
import app.notificadorweb.exception.PedidoNaoEncontradoException;
import app.notificadorweb.exception.StatusPedidoInvalidoException;
import org.springframework.stereotype.Service;
import app.notificadorweb.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    private final SmsService smsService;


    public PedidoService(PedidoRepository pedidoRepository, SmsService smsService) {
        this.pedidoRepository = pedidoRepository;
        this.smsService = smsService;

    }

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }



    public Pedido criarPedido(Long produtoId, String nomeCliente, String telefoneCliente) {
        String nomeProduto = resolverNomeProduto(produtoId);

        String codigoRastreio = gerarCodigoRastreio();

        Pedido pedido = new Pedido(nomeProduto, codigoRastreio);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoSalvo;
    }

    public Pedido atualizarStatus(Long pedidoId, String novoStatus) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));

        StatusPedido statusConvertido;
        try {
            statusConvertido = StatusPedido.valueOf(novoStatus);
        } catch (IllegalArgumentException e) {
            throw new StatusPedidoInvalidoException(novoStatus);
        }

        pedido.atualizarStatus(statusConvertido);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        smsService.enviarSms(
                "Vitor",
                "+5585999580201",
                pedidoSalvo.getId(), pedidoSalvo.getNomeProduto(),
                pedidoSalvo.getStatus()
        );

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
