export interface IMaquina {
  id?: number;
  nome?: string;
}

export class Maquina implements IMaquina {
  constructor(public id?: number, public nome?: string) {}
}
