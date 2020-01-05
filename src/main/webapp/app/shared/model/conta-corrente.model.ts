import { Moment } from 'moment';
import { MetodoPagamento } from 'app/shared/model/enumerations/metodo-pagamento.model';
import { SituacaoContaCorrente } from 'app/shared/model/enumerations/situacao-conta-corrente.model';

export interface IContaCorrente {
  id?: number;
  valor?: number;
  data?: Moment;
  descricao?: string;
  metodoPagamento?: MetodoPagamento;
  situacao?: SituacaoContaCorrente;
  receitaId?: number;
  receitaDescricao?: string;
  despesaId?: number;
  despesaDescricao?: string;
  operadorCaixaId?: number;
  operadorCaixaNome?: string;
  lojaId?: number;
  lojaNomeFantasia?: string;
}

export class ContaCorrente implements IContaCorrente {
  constructor(
    public id?: number,
    public valor?: number,
    public data?: Moment,
    public descricao?: string,
    public metodoPagamento?: MetodoPagamento,
    public situacao?: SituacaoContaCorrente,
    public receitaId?: number,
    public despesaId?: number,
    public operadorCaixaId?: number,
    public lojaId?: number
  ) {}
}
