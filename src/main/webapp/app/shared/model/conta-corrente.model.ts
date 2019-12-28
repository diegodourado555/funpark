import { Moment } from 'moment';
import { MetodoPagamento } from 'app/shared/model/enumerations/metodo-pagamento.model';

export interface IContaCorrente {
  id?: number;
  valor?: number;
  data?: Moment;
  metodoPagamento?: MetodoPagamento;
  receitaId?: number;
  despesaId?: number;
  operadorCaixaId?: number;
  lojaId?: number;
}

export class ContaCorrente implements IContaCorrente {
  constructor(
    public id?: number,
    public valor?: number,
    public data?: Moment,
    public metodoPagamento?: MetodoPagamento,
    public receitaId?: number,
    public despesaId?: number,
    public operadorCaixaId?: number,
    public lojaId?: number
  ) {}
}
