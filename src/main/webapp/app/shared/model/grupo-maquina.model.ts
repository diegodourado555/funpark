export interface IGrupoMaquina {
  id?: number;
  nome?: string;
  maquinaId?: number;
}

export class GrupoMaquina implements IGrupoMaquina {
  constructor(public id?: number, public nome?: string, public maquinaId?: number) {}
}
