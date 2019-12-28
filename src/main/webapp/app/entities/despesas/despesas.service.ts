import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDespesas } from 'app/shared/model/despesas.model';

type EntityResponseType = HttpResponse<IDespesas>;
type EntityArrayResponseType = HttpResponse<IDespesas[]>;

@Injectable({ providedIn: 'root' })
export class DespesasService {
  public resourceUrl = SERVER_API_URL + 'api/despesas';

  constructor(protected http: HttpClient) {}

  create(despesas: IDespesas): Observable<EntityResponseType> {
    return this.http.post<IDespesas>(this.resourceUrl, despesas, { observe: 'response' });
  }

  update(despesas: IDespesas): Observable<EntityResponseType> {
    return this.http.put<IDespesas>(this.resourceUrl, despesas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDespesas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDespesas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
