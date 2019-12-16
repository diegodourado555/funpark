export interface IGrupoMaquina {
  id?: number;
  nome?: string;
}

export class GrupoMaquina implements IGrupoMaquina {
  constructor(public id?: number, public nome?: string) {}
}
