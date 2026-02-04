package com.projeto.urbana.controller;

import com.projeto.urbana.dto.*;
import com.projeto.urbana.service.CartaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/usuarios/{usuarioId}/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    // ===== Listar todos os cartões de um usuário =====
    @GetMapping
    public ResponseEntity<ApiResponse<List<CartaoRetornoDTO>>> listarCartoes(@PathVariable Long usuarioId) {
        try {
            List<CartaoRetornoDTO> cartoes = cartaoService.listarCartoes(usuarioId);
            return ResponseEntity.ok(new ApiResponse<>("Lista de cartões", cartoes));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Consultar cartão específico =====
    @GetMapping("/{cartaoId}")
    public ResponseEntity<ApiResponse<CartaoRetornoDTO>> consultarCartao(@PathVariable Long usuarioId,
                                                                         @PathVariable Long cartaoId) {
        try {
            CartaoRetornoDTO cartao = cartaoService.consultarCartao(usuarioId, cartaoId);
            return ResponseEntity.ok(new ApiResponse<>("Cartão encontrado", cartao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Alterar status do cartão =====
    @PutMapping("/{cartaoId}/status")
    public ResponseEntity<ApiResponse<CartaoRetornoDTO>> alterarStatus(@PathVariable Long usuarioId,
                                                                       @PathVariable Long cartaoId,
                                                                       @RequestBody StatusCartaoDTO statusDTO) {
        try {
            CartaoRetornoDTO cartaoAtualizado = cartaoService.alterarStatus(usuarioId, cartaoId, statusDTO.getStatus());
            return ResponseEntity.ok(new ApiResponse<>("Status do cartão atualizado", cartaoAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }

    // ===== Remover cartão =====
    @DeleteMapping("/{cartaoId}")
    public ResponseEntity<ApiResponse<Void>> removerCartao(@PathVariable Long usuarioId, @PathVariable Long cartaoId) {
        try {
            cartaoService.removerCartao(usuarioId, cartaoId);
            return ResponseEntity.ok(new ApiResponse<>("Cartão removido com sucesso", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(e.getMessage(), null));
        }
    }
}
