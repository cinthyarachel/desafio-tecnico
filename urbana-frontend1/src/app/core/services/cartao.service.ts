// src/app/core/services/cartao.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StatusCartao } from '../models/status-cartao.enum'; 
import { CartaoRetornoDTO , CartaoCadastroDTO } from '../models/cartao.model'; 

@Injectable({
  providedIn: 'root'
})
export class CartaoService {

  private apiUrl = 'http://localhost:3000/cartao'; 

  constructor(private http: HttpClient) {}

  alterarStatus(usuarioId: number, cartaoId: number, status: StatusCartao): Observable<CartaoRetornoDTO> {
    return this.http.put<CartaoRetornoDTO>(`${this.apiUrl}/alterarStatus/${usuarioId}/${cartaoId}`, { status });
  }

  listarCartoes(usuarioId: number): Observable<CartaoRetornoDTO[]> {
    return this.http.get<CartaoRetornoDTO[]>(`${this.apiUrl}/listar/${usuarioId}`);
  }

  criar(cartao: CartaoCadastroDTO): Observable<CartaoCadastroDTO> {
    return this.http.post<CartaoCadastroDTO>(this.apiUrl, cartao);
  }
}
