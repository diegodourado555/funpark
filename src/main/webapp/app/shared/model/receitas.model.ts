export interface IReceitas {
  id?: number;
  descricao?: string;
}

export class Receitas implements IReceitas {
  constructor(public id?: number, public descricao?: string) {}
}
