import { CartaoRetornoDTO } from './cartao.model'; 

export interface UsuarioRetornoDTO {
  id: number;
  nome: string;
  email: string;
  cartoes: CartaoRetornoDTO[];
}
