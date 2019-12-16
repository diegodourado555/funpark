export interface IDespesas {
  id?: number;
  descricao?: string;
}

export class Despesas implements IDespesas {
  constructor(public id?: number, public descricao?: string) {}
}
