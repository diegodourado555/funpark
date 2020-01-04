import { Moment } from 'moment';
import { SituacaoMaquina } from 'app/shared/model/enumerations/situacao-maquina.model';

export interface IHistoricoMaquina {
  id?: number;
  nome?: string;
  data?: Moment;
  situacao?: SituacaoMaquina;
}

export class HistoricoMaquina implements IHistoricoMaquina {
  constructor(public id?: number, public nome?: string, public data?: Moment, public situacao?: SituacaoMaquina) {}
}
