import { SituacaoOperadorCaixa } from 'app/shared/model/enumerations/situacao-operador-caixa.model';

export interface IOperadorCaixa {
  id?: number;
  nome?: string;
  cpf?: number;
  situacao?: SituacaoOperadorCaixa;
  lojaId?: number;
}

export class OperadorCaixa implements IOperadorCaixa {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: number,
    public situacao?: SituacaoOperadorCaixa,
    public lojaId?: number
  ) {}
}
