package app.notificadorweb.controller;

import app.notificadorweb.domain.Pedido;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import app.notificadorweb.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido criarPedido(@RequestParam String nomeProduto,
                              @RequestParam String codigoRastreio){
        return pedidoService.criarPedido(nomeProduto, codigoRastreio);
    }


}
