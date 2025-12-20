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

    public Pedido criarPedido(Long produtoId, String nomeCliente, String telefoneCliente){
        String nomeProduto = resolverNomeProduto(produtoId);

        String codigoRastreio = gerarCodigoRastreio();

        Pedido pedido = new Pedido(nomeProduto, codigoRastreio);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return pedidoSalvo;
    }


    private String resolverNomeProduto(Long produtoId){
        if (produtoId == 1L){
            return "Moto G";
        }
        if (produtoId == 2L){
            return "Samsung Galaxi";
        }
        if (produtoId == 3L){
            return "Redmi Note 15";
        }

        return "Produto desconhecido";
    }

    private String gerarCodigoRastreio(){
        return "RAST-" + System.currentTimeMillis();
    }
}
