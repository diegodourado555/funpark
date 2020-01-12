import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IContaCorrente } from 'app/shared/model/conta-corrente.model';

type EntityResponseType = HttpResponse<IContaCorrente>;
type EntityArrayResponseType = HttpResponse<IContaCorrente[]>;

@Injectable({ providedIn: 'root' })
export class ContaCorrenteService {
  public resourceUrl = SERVER_API_URL + 'api/conta-correntes';

  constructor(protected http: HttpClient) {}

  create(contaCorrente: IContaCorrente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contaCorrente);
    return this.http
      .post<IContaCorrente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(contaCorrente: IContaCorrente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(contaCorrente);
    return this.http
      .put<IContaCorrente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IContaCorrente>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IContaCorrente[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  search(descricaoFilter: string): Observable<EntityArrayResponseType> {
    if (descricaoFilter) {
      return this.http
        .get<IContaCorrente[]>(`${this.resourceUrl}/search/${descricaoFilter}`, { observe: 'response' })
        .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    } else {
      return this.http
        .get<IContaCorrente[]>(`${this.resourceUrl}`, { observe: 'response' })
        .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(contaCorrente: IContaCorrente): IContaCorrente {
    const copy: IContaCorrente = Object.assign({}, contaCorrente, {
      data: contaCorrente.data && contaCorrente.data.isValid() ? contaCorrente.data.toJSON() : undefined
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
      res.body.forEach((contaCorrente: IContaCorrente) => {
        contaCorrente.data = contaCorrente.data ? moment(contaCorrente.data) : undefined;
      });
    }
    return res;
  }
}
