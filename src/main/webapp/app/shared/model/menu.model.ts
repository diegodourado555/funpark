export interface IMenu {
  id?: number;
  descricao?: string;
}

export class Menu implements IMenu {
  constructor(public id?: number, public descricao?: string) {}
}
