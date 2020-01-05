export interface ILojaMaquina {
  id?: number;
  maquinaId?: number;
  maquinaNome?: string;
  lojaId?: number;
  lojaNomeFantasia?: string;
}

export class LojaMaquina implements ILojaMaquina {
  constructor(public id?: number, public maquinaId?: number, public lojaId?: number) {}
}
