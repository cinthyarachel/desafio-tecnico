import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../core/services/usuario.service';
import { UsuarioRetornoDTO } from '../../core/models/usuario.model';
import { ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  template: `
    <h2>Usuários</h2>
    <div *ngIf="usuarios?.length > 0; else semUsuarios">
      <ul>
        <li *ngFor="let usuario of usuarios">
          <div>
            <p><strong>Nome:</strong> {{ usuario.nome }}</p>
            <p><strong>Email:</strong> {{ usuario.email }}</p>
            <button (click)="editar(usuario.id)">Editar</button>
            <button (click)="excluir(usuario.id)">Excluir</button>
          </div>
          <hr />
        </li>
      </ul>
    </div>
    <ng-template #semUsuarios>
      <p>Não há usuários cadastrados.</p>
    </ng-template>
  `,
})
export class UsuarioListComponent implements OnInit {
  usuarios: UsuarioRetornoDTO[] = [];

  constructor(
    private usuarioService: UsuarioService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.listarUsuarios();
  }

  // Método para listar os usuários
  listarUsuarios(): void {
    this.usuarioService.listar().subscribe(
      (data) => {
        this.usuarios = data; 
        this.changeDetectorRef.detectChanges();
      },
      (error) => {
        console.error('Erro ao listar usuários', error);
        alert('Erro ao carregar lista de usuários');
      }
    );
  }

  editar(id: number): void {
    console.log('Editando usuário com id:', id);
  }

  excluir(id: number): void {
    console.log('Excluindo usuário com id:', id);
    
  }
}
