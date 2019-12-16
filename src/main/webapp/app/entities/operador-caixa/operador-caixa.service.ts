import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOperadorCaixa } from 'app/shared/model/operador-caixa.model';

type EntityResponseType = HttpResponse<IOperadorCaixa>;
type EntityArrayResponseType = HttpResponse<IOperadorCaixa[]>;

@Injectable({ providedIn: 'root' })
export class OperadorCaixaService {
  public resourceUrl = SERVER_API_URL + 'api/operador-caixas';

  constructor(protected http: HttpClient) {}

  create(operadorCaixa: IOperadorCaixa): Observable<EntityResponseType> {
    return this.http.post<IOperadorCaixa>(this.resourceUrl, operadorCaixa, { observe: 'response' });
  }

  update(operadorCaixa: IOperadorCaixa): Observable<EntityResponseType> {
    return this.http.put<IOperadorCaixa>(this.resourceUrl, operadorCaixa, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOperadorCaixa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOperadorCaixa[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
