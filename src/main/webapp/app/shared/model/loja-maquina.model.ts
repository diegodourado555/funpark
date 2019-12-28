export interface ILojaMaquina {
  id?: number;
  maquinaId?: number;
  lojaId?: number;
}

export class LojaMaquina implements ILojaMaquina {
  constructor(public id?: number, public maquinaId?: number, public lojaId?: number) {}
}
