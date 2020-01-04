import { Moment } from 'moment';
import { SituacaoOperadorCaixa } from 'app/shared/model/enumerations/situacao-operador-caixa.model';

export interface IHistoricoOperadorCaixa {
  id?: number;
  nome?: string;
  cpf?: number;
  data?: Moment;
  situacao?: SituacaoOperadorCaixa;
}

export class HistoricoOperadorCaixa implements IHistoricoOperadorCaixa {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: number,
    public data?: Moment,
    public situacao?: SituacaoOperadorCaixa
  ) {}
}
