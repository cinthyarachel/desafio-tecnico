import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';  // Importe o FormsModule aqui
import { UsuarioService } from '../../core/services/usuario.service';
import { Router } from '@angular/router';
import { UsuarioCadastroDTO } from '../../core/models/usuario.model';

@Component({
  selector: 'app-usuario-form',
  standalone: true, // componente standalone
  imports: [CommonModule, FormsModule],  // Adicione o FormsModule aqui
  template: `
    <h2>{{ usuario.id ? 'Editar Usuário' : 'Novo Usuário' }}</h2>
    <form (ngSubmit)="salvar()">
      <label>
        Nome:
        <input type="text" [(ngModel)]="usuario.nome" name="nome" required />
      </label>
      <br /><br />

      <label>
        Email:
        <input type="email" [(ngModel)]="usuario.email" name="email" required />
      </label>
      <br /><br />

      <label>
        Senha:
        <input
          type="password"
          [(ngModel)]="usuario.senha"
          name="senha"
          [disabled]="!usuario.id"
          required
        />
      </label>
      <br /><br />

      <button type="submit">Salvar</button>
      <button type="button" (click)="voltar()">Cancelar</button>
    </form>
  `
})
export class UsuarioFormComponent {
  usuario: UsuarioCadastroDTO = {
    id: 0,
    nome: '',
    email: '',
    senha: ''
  };

  constructor(
    private usuarioService: UsuarioService,
    private router: Router
  ) {}

  salvar(): void {
    if (this.usuario.id) {
      this.usuarioService.editar(this.usuario.id, this.usuario).subscribe(() => {
        alert('Usuário atualizado com sucesso!');
        this.router.navigate(['/usuarios']);
      });
    } else {
      this.usuarioService.criar(this.usuario).subscribe(() => {
        alert('Usuário criado com sucesso!');
        this.router.navigate(['/usuarios']);
      });
    }
  }

  voltar(): void {
    this.router.navigate(['/usuarios']);
  }
}
