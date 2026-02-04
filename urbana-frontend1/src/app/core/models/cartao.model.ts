// Importando StatusCartao do status-cartao.enum.ts corretamente.
import { StatusCartao } from './status-cartao.enum';

// Definindo o modelo para o cadastro de cartão.
export interface CartaoCadastroDTO {
  numeroCartao: string;
  tipo: string;
  status: StatusCartao;
}

// Definindo o modelo para o retorno do cartão da API.
export interface CartaoRetornoDTO {
  id: number;
  numeroCartao: string;
  tipo: string;
  status: StatusCartao;
}
