package com.projeto.urbana.dto;

public class ApiResponse<T> {
    private String mensagem;
    private T data;

    public ApiResponse(String mensagem, T data) {
        this.mensagem = mensagem;
        this.data = data;
    }

    public String getMensagem() { return mensagem; }
    public T getData() { return data; }
}
