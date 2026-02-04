package com.projeto.urbana.service;

import com.projeto.urbana.dto.*;
import com.projeto.urbana.entity.Usuario;
import com.projeto.urbana.entity.Cartao;
import com.projeto.urbana.tipos.StatusCartao;
import com.projeto.urbana.repository.UsuarioRepository;
import com.projeto.urbana.repository.CartaoRepository;
import com.projeto.urbana.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    private final UsuarioRepository usuarioRepository;
    private final CartaoRepository cartaoRepository;

    public CartaoService(UsuarioRepository usuarioRepository, CartaoRepository cartaoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    public CartaoRetornoDTO adicionarCartao(Long usuarioId, CartaoCadastroDTO dto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Cartao cartao = new Cartao(dto.getNumeroCartao(), dto.getNome(), dto.getTipo(), StatusCartao.ATIVO);
        cartao.setUsuario(usuario);

        cartaoRepository.save(cartao);

        return new CartaoRetornoDTO(cartao.getId(), cartao.getNumeroCartao(), cartao.getNome(), cartao.getTipo(), cartao.getStatus());
    }

    @Transactional
    public CartaoRetornoDTO alterarStatus(Long usuarioId, Long cartaoId, StatusCartao status) {
        Cartao cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));

        if (!cartao.getUsuario().getId().equals(usuarioId))
            throw new IllegalArgumentException("O cartão não pertence ao usuário informado");

        cartao.setStatus(status);
        cartaoRepository.save(cartao);

        return new CartaoRetornoDTO(cartao.getId(), cartao.getNumeroCartao(), cartao.getNome(), cartao.getTipo(), cartao.getStatus());
    }

    @Transactional(readOnly = true)
    public CartaoRetornoDTO consultarCartao(Long usuarioId, Long cartaoId) {
        Cartao cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));

        if (!cartao.getUsuario().getId().equals(usuarioId))
            throw new IllegalArgumentException("O cartão não pertence ao usuário informado");

        return new CartaoRetornoDTO(cartao.getId(), cartao.getNumeroCartao(), cartao.getNome(), cartao.getTipo(), cartao.getStatus());
    }

    @Transactional(readOnly = true)
    public List<CartaoRetornoDTO> listarCartoes(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return usuario.getCartoes().stream()
                .map(c -> new CartaoRetornoDTO(c.getId(), c.getNumeroCartao(), c.getNome(), c.getTipo(), c.getStatus()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void removerCartao(Long usuarioId, Long cartaoId) {
        Cartao cartao = cartaoRepository.findById(cartaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cartão não encontrado"));

        if (!cartao.getUsuario().getId().equals(usuarioId))
            throw new IllegalArgumentException("O cartão não pertence ao usuário informado");

        cartaoRepository.delete(cartao);
    }
}
