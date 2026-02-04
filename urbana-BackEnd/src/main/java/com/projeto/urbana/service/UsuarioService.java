package com.projeto.urbana.service;

import com.projeto.urbana.dto.*;
import com.projeto.urbana.entity.Usuario;
import com.projeto.urbana.entity.Cartao;
import com.projeto.urbana.tipos.StatusCartao;
import com.projeto.urbana.repository.UsuarioRepository;
import com.projeto.urbana.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioRetornoDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        if (dto.getCartoes() != null && !dto.getCartoes().isEmpty()) {
            List<Cartao> cartoes = dto.getCartoes().stream()
                    .map(c -> {
                        Cartao cartao = new Cartao(c.getNumeroCartao(), c.getNome(), c.getTipo(), StatusCartao.ATIVO);
                        cartao.setUsuario(usuario);
                        return cartao;
                    }).collect(Collectors.toList());
            usuario.setCartoes(cartoes);
        }

        usuarioRepository.save(usuario);

        List<CartaoRetornoDTO> cartoesRetorno = usuario.getCartoes().stream()
                .map(c -> new CartaoRetornoDTO(c.getId(), c.getNumeroCartao(), c.getNome(), c.getTipo(), c.getStatus()))
                .collect(Collectors.toList());

        return new UsuarioRetornoDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), cartoesRetorno);
    }

    @Transactional(readOnly = true)
    public UsuarioRetornoDTO consultarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        List<CartaoRetornoDTO> cartoes = usuario.getCartoes().stream()
                .map(c -> new CartaoRetornoDTO(c.getId(), c.getNumeroCartao(), c.getNome(), c.getTipo(), c.getStatus()))
                .collect(Collectors.toList());

        return new UsuarioRetornoDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), cartoes);
    }

    @Transactional(readOnly = true)
    public List<UsuarioRetornoDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> {
                    List<CartaoRetornoDTO> cartoes = u.getCartoes().stream()
                            .map(c -> new CartaoRetornoDTO(c.getId(), c.getNumeroCartao(), c.getNome(), c.getTipo(), c.getStatus()))
                            .collect(Collectors.toList());
                    return new UsuarioRetornoDTO(u.getId(), u.getNome(), u.getEmail(), cartoes);
                }).collect(Collectors.toList());
    }

    @Transactional
    public UsuarioRetornoDTO atualizarUsuario(Long id, UsuarioCadastroDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!usuario.getEmail().equals(dto.getEmail()) && usuarioRepository.existsByEmail(dto.getEmail()))
            throw new IllegalArgumentException("E-mail já cadastrado");

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        usuarioRepository.save(usuario);

        List<CartaoRetornoDTO> cartoes = usuario.getCartoes().stream()
                .map(c -> new CartaoRetornoDTO(c.getId(), c.getNumeroCartao(), c.getNome(), c.getTipo(), c.getStatus()))
                .collect(Collectors.toList());

        return new UsuarioRetornoDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), cartoes);
    }

    @Transactional
    public void removerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
}
