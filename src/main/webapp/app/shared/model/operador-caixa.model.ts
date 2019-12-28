export interface IOperadorCaixa {
  id?: number;
  nome?: string;
  cpf?: number;
  lojaId?: number;
}

export class OperadorCaixa implements IOperadorCaixa {
  constructor(public id?: number, public nome?: string, public cpf?: number, public lojaId?: number) {}
}
