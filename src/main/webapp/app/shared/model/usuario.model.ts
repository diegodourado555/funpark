export interface IUsuario {
  id?: number;
  login?: string;
  senha?: string;
  nome?: string;
  cpf?: number;
  email?: string;
  telefone?: string;
  endereco?: string;
}

export class Usuario implements IUsuario {
  constructor(
    public id?: number,
    public login?: string,
    public senha?: string,
    public nome?: string,
    public cpf?: number,
    public email?: string,
    public telefone?: string,
    public endereco?: string
  ) {}
}
