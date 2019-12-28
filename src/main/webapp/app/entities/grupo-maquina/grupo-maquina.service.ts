import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';

type EntityResponseType = HttpResponse<IGrupoMaquina>;
type EntityArrayResponseType = HttpResponse<IGrupoMaquina[]>;

@Injectable({ providedIn: 'root' })
export class GrupoMaquinaService {
  public resourceUrl = SERVER_API_URL + 'api/grupo-maquinas';

  constructor(protected http: HttpClient) {}

  create(grupoMaquina: IGrupoMaquina): Observable<EntityResponseType> {
    return this.http.post<IGrupoMaquina>(this.resourceUrl, grupoMaquina, { observe: 'response' });
  }

  update(grupoMaquina: IGrupoMaquina): Observable<EntityResponseType> {
    return this.http.put<IGrupoMaquina>(this.resourceUrl, grupoMaquina, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGrupoMaquina>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGrupoMaquina[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
