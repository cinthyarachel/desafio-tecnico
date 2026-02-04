package com.projeto.urbana.dto;

import java.util.List;

public class UsuarioCadastroDTO {
    private String nome;
    private String email;
    private String senha;
    private List<CartaoCadastroDTO> cartoes;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<CartaoCadastroDTO> getCartoes() { return cartoes; }
    public void setCartoes(List<CartaoCadastroDTO> cartoes) { this.cartoes = cartoes; }
}
