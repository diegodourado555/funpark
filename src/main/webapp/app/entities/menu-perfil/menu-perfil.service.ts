import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMenuPerfil } from 'app/shared/model/menu-perfil.model';

type EntityResponseType = HttpResponse<IMenuPerfil>;
type EntityArrayResponseType = HttpResponse<IMenuPerfil[]>;

@Injectable({ providedIn: 'root' })
export class MenuPerfilService {
  public resourceUrl = SERVER_API_URL + 'api/menu-perfils';

  constructor(protected http: HttpClient) {}

  create(menuPerfil: IMenuPerfil): Observable<EntityResponseType> {
    return this.http.post<IMenuPerfil>(this.resourceUrl, menuPerfil, { observe: 'response' });
  }

  update(menuPerfil: IMenuPerfil): Observable<EntityResponseType> {
    return this.http.put<IMenuPerfil>(this.resourceUrl, menuPerfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMenuPerfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMenuPerfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
