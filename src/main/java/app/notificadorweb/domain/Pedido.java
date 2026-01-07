package app.notificadorweb.domain;

import app.notificadorweb.exception.PedidoNaoPodeSerAtualizadoException;
import app.notificadorweb.exception.PedidoNaoPodeSerDeletadoException;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private String codigoRastreio;

    private boolean ativo;
    private LocalDateTime dataExclusao;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private StatusPedido status;
    private LocalDateTime dataUltimaAtualizacao;

    protected Pedido(){}

    public Pedido(String nomeProduto, String codigoRastreio) {
        this.nomeProduto = nomeProduto;
        this.codigoRastreio = codigoRastreio;
        this.status = StatusPedido.CRIADO;
        this.dataUltimaAtualizacao = LocalDateTime.now();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void atualizarStatus(StatusPedido novoStatus) {
        if (!this.ativo){
            throw new PedidoNaoPodeSerAtualizadoException("O pedido está inativo, logo não pode mudar o status");
        }

        this.status = novoStatus;
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }

    public void excluir(){
        if (this.status != StatusPedido.CRIADO) {
            throw new PedidoNaoPodeSerDeletadoException(this.id, this.status);
        }

        this.ativo = false;
        this.dataExclusao = LocalDateTime.now();
    }



    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }


}
