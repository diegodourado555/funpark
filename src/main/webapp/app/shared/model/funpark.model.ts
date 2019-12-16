export interface IFunpark {
  id?: number;
  nome?: string;
}

export class Funpark implements IFunpark {
  constructor(public id?: number, public nome?: string) {}
}
