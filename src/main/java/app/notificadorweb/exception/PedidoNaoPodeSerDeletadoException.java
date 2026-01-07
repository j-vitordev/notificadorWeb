package app.notificadorweb.exception;

import app.notificadorweb.domain.StatusPedido;

public class PedidoNaoPodeSerDeletadoException extends RuntimeException{
    public PedidoNaoPodeSerDeletadoException(Long pedidoId, StatusPedido statusAtual) {
        super("Pedido " + pedidoId + " não pode ser excluído, pois o status é " +  statusAtual);
    }
}
