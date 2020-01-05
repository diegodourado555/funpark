import { SituacaoMaquina } from 'app/shared/model/enumerations/situacao-maquina.model';

export interface IMaquina {
  id?: number;
  nome?: string;
  situacao?: SituacaoMaquina;
  grupoMaquinaId?: number;
  grupoMaquinaNome?: string;
}

export class Maquina implements IMaquina {
  constructor(public id?: number, public nome?: string, public situacao?: SituacaoMaquina, public grupoMaquinaId?: number) {}
}
