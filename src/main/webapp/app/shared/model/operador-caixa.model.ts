import { SituacaoOperadorCaixa } from 'app/shared/model/enumerations/situacao-operador-caixa.model';

export interface IOperadorCaixa {
  id?: number;
  nome?: string;
  cpf?: string;
  situacao?: SituacaoOperadorCaixa;
  lojaId?: number;
  lojaNomeFantasia?: string;
}

export class OperadorCaixa implements IOperadorCaixa {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: string,
    public situacao?: SituacaoOperadorCaixa,
    public lojaId?: number
  ) {}
}
