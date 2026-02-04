import { TipoCartao } from './tipo-cartao.enum'; 
import { CartaoCadastroDTO } from './cartao.model'; 
import { CartaoRetornoDTO } from './cartao.model'; 

export interface UsuarioCadastroDTO {
  id: number;
  nome: string;
  email: string;
  senha: string; 
}

export interface UsuarioRetornoDTO {
  id: number;
  nome: string;
  email: string;
  senha?: string; 
}
