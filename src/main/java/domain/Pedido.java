package domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String codigoRastreio;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private LocalDateTime dataUltimaAtualizacao;

    protected Pedido(){}

    public Pedido(String nomeProduto, String codigoRastreio) {
        this.nomeProduto = nomeProduto;
        this.codigoRastreio = codigoRastreio;
        this.status = StatusPedido.CRIADO;
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }


}
