import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

type EntityResponseType = HttpResponse<IHistoricoOperadorCaixa>;
type EntityArrayResponseType = HttpResponse<IHistoricoOperadorCaixa[]>;

@Injectable({ providedIn: 'root' })
export class HistoricoOperadorCaixaService {
  public resourceUrl = SERVER_API_URL + 'api/historico-operador-caixas';

  constructor(protected http: HttpClient) {}

  create(historicoOperadorCaixa: IHistoricoOperadorCaixa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoOperadorCaixa);
    return this.http
      .post<IHistoricoOperadorCaixa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historicoOperadorCaixa: IHistoricoOperadorCaixa): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoOperadorCaixa);
    return this.http
      .put<IHistoricoOperadorCaixa>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistoricoOperadorCaixa>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistoricoOperadorCaixa[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historicoOperadorCaixa: IHistoricoOperadorCaixa): IHistoricoOperadorCaixa {
    const copy: IHistoricoOperadorCaixa = Object.assign({}, historicoOperadorCaixa, {
      data:
        historicoOperadorCaixa.data && historicoOperadorCaixa.data.isValid() ? historicoOperadorCaixa.data.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.data = res.body.data ? moment(res.body.data) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((historicoOperadorCaixa: IHistoricoOperadorCaixa) => {
        historicoOperadorCaixa.data = historicoOperadorCaixa.data ? moment(historicoOperadorCaixa.data) : undefined;
      });
    }
    return res;
  }
}
