export interface IMaquina {
  id?: number;
  nome?: string;
  grupoMaquinaId?: number;
}

export class Maquina implements IMaquina {
  constructor(public id?: number, public nome?: string, public grupoMaquinaId?: number) {}
}
