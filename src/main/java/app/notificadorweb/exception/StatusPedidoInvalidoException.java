package app.notificadorweb.exception;

public class StatusPedidoInvalidoException extends RuntimeException {

    public StatusPedidoInvalidoException(String status) {
        super("Status inválido: " + status);
    }
}
