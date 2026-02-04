package com.projeto.urbana.entity;

import com.projeto.urbana.tipos.StatusCartao;
import com.projeto.urbana.tipos.TipoCartao;
import jakarta.persistence.*;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCartao tipo;

    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtores
    public Cartao() {}
    public Cartao(String numeroCartao, String nome, TipoCartao tipo, StatusCartao status) {
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoCartao getTipo() { return tipo; }
    public void setTipo(TipoCartao tipo) { this.tipo = tipo; }

    public StatusCartao getStatus() { return status; }
    public void setStatus(StatusCartao status) { this.status = status; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
