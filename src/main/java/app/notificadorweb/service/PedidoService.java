package app.notificadorweb.service;

import app.notificadorweb.domain.Pedido;
import org.springframework.stereotype.Service;
import app.notificadorweb.repository.PedidoRepository;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;

    }

    public Pedido criarPedido(String nomeProduto, String codigoRastreio){
        Pedido pedido = new Pedido(nomeProduto, codigoRastreio);
        return pedidoRepository.save(pedido);
    }
}
