export interface ILoja {
  id?: number;
  nomeFantasia?: string;
  razaoSocial?: string;
  cNPJ?: string;
  endereco?: string;
}

export class Loja implements ILoja {
  constructor(
    public id?: number,
    public nomeFantasia?: string,
    public razaoSocial?: string,
    public cNPJ?: string,
    public endereco?: string
  ) {}
}
