export interface IPerfilAcesso {
  id?: number;
  descricao?: string;
}

export class PerfilAcesso implements IPerfilAcesso {
  constructor(public id?: number, public descricao?: string) {}
}
