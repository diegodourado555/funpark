import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMaquina } from 'app/shared/model/maquina.model';

type EntityResponseType = HttpResponse<IMaquina>;
type EntityArrayResponseType = HttpResponse<IMaquina[]>;

@Injectable({ providedIn: 'root' })
export class MaquinaService {
  public resourceUrl = SERVER_API_URL + 'api/maquinas';

  constructor(protected http: HttpClient) {}

  create(maquina: IMaquina): Observable<EntityResponseType> {
    return this.http.post<IMaquina>(this.resourceUrl, maquina, { observe: 'response' });
  }

  update(maquina: IMaquina): Observable<EntityResponseType> {
    return this.http.put<IMaquina>(this.resourceUrl, maquina, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMaquina>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMaquina[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
