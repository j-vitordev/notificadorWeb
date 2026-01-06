package app.notificadorweb.service;

import app.notificadorweb.domain.Pedido;
import app.notificadorweb.domain.StatusPedido;
import app.notificadorweb.exception.PedidoNaoEncontradoException;
import app.notificadorweb.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private SmsService smsService;

    @InjectMocks
    private PedidoService pedidoService;

    @DisplayName("Dado um pedido, quando chamar listagem, então deve retornar todos os pedidos")
    @Test
    void deveRetornarTodosOsPedidos() {
        Pedido pedido1 = new Pedido("Mackbook pró", "xr3");
        Pedido pedido2 = new Pedido("Mackbook air", "xr4");

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido1, pedido2));

        List<Pedido> pedidos = pedidoService.listarPedidos();

        assertEquals(2, pedidos.size());
        verify(pedidoRepository).findAll();
    }

    @DisplayName("Dado um pedido, quando chamar por id o pedido, então deve retornar o pedido")
    @Test
    void buscarPedidoPorId() {

        Pedido pedido = new Pedido("Air Fry", "xr5");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido pedidoRetornado = pedidoService.buscarPedidoPorId(1L);

        assertNotNull(pedidoRetornado);
        assertEquals("Air Fry", pedidoRetornado.getNomeProduto());

        verify(pedidoRepository).findById(1L);

    }

    @DisplayName("Dado um pedido inexistente, quando buscar pedido, então deve lançar exceção")
    @Test
    void deveLancarException() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PedidoNaoEncontradoException.class, () -> pedidoService.buscarPedidoPorId(1L));

        verify(pedidoRepository).findById(1L);
    }

    @DisplayName("Dado um produto válido, quando criar pedido, então deve persistir e retornar o pedido")
    @Test
    void criarPedido() {

        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation ->
                invocation.getArgument(0));
        Pedido pedidoSalvo = pedidoService.
                criarPedido(1L, "Vitor", "85999999999");


        assertNotNull(pedidoSalvo);
        assertEquals("Moto G", pedidoSalvo.getNomeProduto());
        assertNotNull(pedidoSalvo.getCodigoRastreio());
        assertEquals(StatusPedido.CRIADO, pedidoSalvo.getStatus());

        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void atualizarStatus() {
    }
}