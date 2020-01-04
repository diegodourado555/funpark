export interface IDespesas {
  id?: number;
  codigo?: string;
  descricao?: string;
}

export class Despesas implements IDespesas {
  constructor(public id?: number, public codigo?: string, public descricao?: string) {}
}
