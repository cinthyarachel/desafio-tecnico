// src/app/models/api-response.model.ts
export interface ApiResponse<T> {
  mensagem: string;
  data: T;
}
