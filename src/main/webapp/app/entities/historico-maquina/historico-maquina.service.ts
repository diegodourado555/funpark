import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistoricoMaquina } from 'app/shared/model/historico-maquina.model';

type EntityResponseType = HttpResponse<IHistoricoMaquina>;
type EntityArrayResponseType = HttpResponse<IHistoricoMaquina[]>;

@Injectable({ providedIn: 'root' })
export class HistoricoMaquinaService {
  public resourceUrl = SERVER_API_URL + 'api/historico-maquinas';

  constructor(protected http: HttpClient) {}

  create(historicoMaquina: IHistoricoMaquina): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoMaquina);
    return this.http
      .post<IHistoricoMaquina>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historicoMaquina: IHistoricoMaquina): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historicoMaquina);
    return this.http
      .put<IHistoricoMaquina>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistoricoMaquina>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistoricoMaquina[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historicoMaquina: IHistoricoMaquina): IHistoricoMaquina {
    const copy: IHistoricoMaquina = Object.assign({}, historicoMaquina, {
      data: historicoMaquina.data && historicoMaquina.data.isValid() ? historicoMaquina.data.format(DATE_FORMAT) : undefined
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
      res.body.forEach((historicoMaquina: IHistoricoMaquina) => {
        historicoMaquina.data = historicoMaquina.data ? moment(historicoMaquina.data) : undefined;
      });
    }
    return res;
  }
}
