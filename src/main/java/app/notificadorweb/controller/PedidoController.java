package app.notificadorweb.controller;

import app.notificadorweb.domain.Pedido;
import app.notificadorweb.domain.StatusPedido;
import app.notificadorweb.dto.PedidoRequestDto;
import app.notificadorweb.dto.PedidoStatusRequestDto;
import org.springframework.web.bind.annotation.*;
import app.notificadorweb.service.PedidoService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido>listarPedidos(){
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPedido(@PathVariable Long id){
        return pedidoService.buscarPedidoPorId(id);
    }

    @PostMapping
    public Pedido criarPedido(@RequestBody PedidoRequestDto  pedidoRequestDto) {
        return pedidoService.criarPedido(pedidoRequestDto.getProdutoId(),
                pedidoRequestDto.getNomeCliente(),
                pedidoRequestDto.getTelefoneCliente());
    }

    @PatchMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id,
                                  @RequestBody PedidoStatusRequestDto  pedidoStatusRequestDto) {
        return pedidoService.atualizarStatus(id, pedidoStatusRequestDto.getStatus());
    }


}
