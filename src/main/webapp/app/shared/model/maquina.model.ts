export interface IMaquina {
  id?: number;
  nome?: string;
  idGrupoMaquina?: number;
  grupoMaquinaId?: number;
}

export class Maquina implements IMaquina {
  constructor(public id?: number, public nome?: string, public idGrupoMaquina?: number, public grupoMaquinaId?: number) {}
}
