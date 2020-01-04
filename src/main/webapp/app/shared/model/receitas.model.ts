export interface IReceitas {
  id?: number;
  codigo?: string;
  descricao?: string;
}

export class Receitas implements IReceitas {
  constructor(public id?: number, public codigo?: string, public descricao?: string) {}
}
