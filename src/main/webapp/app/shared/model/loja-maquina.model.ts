export interface ILojaMaquina {
  id?: number;
  idLoja?: number;
  idMaquina?: number;
  maquinaId?: number;
  lojaId?: number;
}

export class LojaMaquina implements ILojaMaquina {
  constructor(public id?: number, public idLoja?: number, public idMaquina?: number, public maquinaId?: number, public lojaId?: number) {}
}
