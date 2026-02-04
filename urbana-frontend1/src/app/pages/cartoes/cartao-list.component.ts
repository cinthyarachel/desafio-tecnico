// src/app/pages/cartoes/cartao-list.component.ts

import { Component, OnInit } from '@angular/core';
import { CartaoService } from '../../core/services/cartao.service';
import { CartaoRetornoDTO } from '../../core/models/cartao.model';
import { StatusCartao } from '../../core/models/status-cartao.enum';

@Component({
  selector: 'app-cartao-list',
  template: `
    <h2>Cartões</h2>
    <div *ngIf="cartoes?.length > 0; else semCartoes">
      <ul>
        <li *ngFor="let cartao of cartoes">
          <div>
            <p><strong>Número do Cartão:</strong> {{ cartao.numeroCartao }}</p>
            <p><strong>Tipo:</strong> {{ cartao.tipo }}</p>
            <p><strong>Status:</strong> {{ cartao.status }}</p>
            <button (click)="alterarStatus(cartao)">
              Alterar Status para {{ cartao.status === StatusCartao.ATIVO ? 'INATIVO' : 'ATIVO' }}
            </button>
          </div>
          <hr />
        </li>
      </ul>
    </div>
    <ng-template #semCartoes>
      <p>Não há cartões cadastrados para este usuário.</p>
    </ng-template>
  `,
})
export class CartaoListComponent implements OnInit {
  cartoes: CartaoRetornoDTO[] = [];
  usuarioId: number = 0; // Inicializando com 0 ou com o valor correto, dependendo da sua lógica
StatusCartao: any;

  constructor(private cartaoService: CartaoService) {}

  ngOnInit(): void {
    // O valor de usuarioId deve vir de alguma outra lógica, como da URL ou serviço
    this.usuarioId = 1; // Substitua conforme necessário
    this.listarCartoes();
  }

  listarCartoes(): void {
    // Chama o serviço para obter os cartões
    this.cartaoService.listarCartoes(this.usuarioId).subscribe((data) => {
      this.cartoes = data;
    });
  }

  alterarStatus(cartao: CartaoRetornoDTO): void {
    const novoStatus =
      cartao.status === StatusCartao.ATIVO
        ? StatusCartao.INATIVO
        : StatusCartao.ATIVO;

    // Atualiza o status do cartão através do serviço
    this.cartaoService
      .alterarStatus(this.usuarioId, cartao.id, novoStatus)
      .subscribe(() => {
        cartao.status = novoStatus; // Atualiza o status no frontend
        alert('Status do cartão alterado com sucesso!');
      });
  }
}
