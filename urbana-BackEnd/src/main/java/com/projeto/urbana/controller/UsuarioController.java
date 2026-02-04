package com.projeto.urbana.controller;

import com.projeto.urbana.dto.*;
import com.projeto.urbana.service.UsuarioService;
import com.projeto.urbana.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final CartaoService cartaoService;

    public UsuarioController(UsuarioService usuarioService, CartaoService cartaoService) {
        this.usuarioService = usuarioService;
        this.cartaoService = cartaoService;
    }

    // ===== Incluir usuário =====
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioRetornoDTO>> cadastrarUsuario(@RequestBody UsuarioCadastroDTO usuarioDTO) {
        try {
            UsuarioRetornoDTO usuarioRetorno = usuarioService.cadastrarUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Usuário cadastrado com sucesso", usuarioRetorno));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("Erro interno do servidor: " + e.getMessage(), null));
        }
    }

    // ===== Consultar usuário específico =====
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioRetornoDTO>> consultarUsuario(@PathVariable Long id) {
        try {
            UsuarioRetornoDTO usuario = usuarioService.consultarUsuario(id);
            return ResponseEntity.ok(new ApiResponse<>("Consulta realizada com sucesso", usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Listar todos os usuários =====
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioRetornoDTO>>> listarUsuarios() {
        List<UsuarioRetornoDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(new ApiResponse<>("Lista de usuários", usuarios));
    }

    // ===== Atualizar usuário =====
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioRetornoDTO>> atualizarUsuario(
            @PathVariable Long id, @RequestBody UsuarioCadastroDTO usuarioDTO) {
        try {
            UsuarioRetornoDTO usuarioAtualizado = usuarioService.atualizarUsuario(id, usuarioDTO);
            return ResponseEntity.ok(new ApiResponse<>("Usuário atualizado com sucesso", usuarioAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Remover usuário =====
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> removerUsuario(@PathVariable Long id) {
        try {
            usuarioService.removerUsuario(id);
            return ResponseEntity.ok(new ApiResponse<>("Usuário removido com sucesso", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Adicionar cartão a usuário =====
    @PostMapping("/{id}/cartoes")
    public ResponseEntity<ApiResponse<CartaoRetornoDTO>> adicionarCartao(
            @PathVariable Long id,
            @RequestBody CartaoCadastroDTO cartaoDTO) {
        try {
            CartaoRetornoDTO cartao = cartaoService.adicionarCartao(id, cartaoDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>("Cartão adicionado com sucesso", cartao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}
