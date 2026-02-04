import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioCadastroDTO, UsuarioRetornoDTO } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private apiUrl = 'http://localhost:3000/usuarios';

  constructor(private http: HttpClient) {}

  listar(): Observable<UsuarioRetornoDTO[]> {
    return this.http.get<UsuarioRetornoDTO[]>(this.apiUrl);
  }

  criar(usuario: UsuarioCadastroDTO): Observable<UsuarioCadastroDTO> {
    return this.http.post<UsuarioCadastroDTO>(this.apiUrl, usuario);
  }

  editar(id: number, usuario: UsuarioCadastroDTO): Observable<UsuarioCadastroDTO> {
    return this.http.put<UsuarioCadastroDTO>(`${this.apiUrl}/${id}`, usuario);
  }

  getById(id: number): Observable<UsuarioRetornoDTO> {
    return this.http.get<UsuarioRetornoDTO>(`${this.apiUrl}/${id}`);
  }
}
