export interface IOperadorCaixa {
  id?: number;
  nome?: string;
  cpf?: number;
  idLoja?: number;
  lojaId?: number;
}

export class OperadorCaixa implements IOperadorCaixa {
  constructor(public id?: number, public nome?: string, public cpf?: number, public idLoja?: number, public lojaId?: number) {}
}
