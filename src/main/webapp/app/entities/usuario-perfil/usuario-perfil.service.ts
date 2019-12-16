import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUsuarioPerfil } from 'app/shared/model/usuario-perfil.model';

type EntityResponseType = HttpResponse<IUsuarioPerfil>;
type EntityArrayResponseType = HttpResponse<IUsuarioPerfil[]>;

@Injectable({ providedIn: 'root' })
export class UsuarioPerfilService {
  public resourceUrl = SERVER_API_URL + 'api/usuario-perfils';

  constructor(protected http: HttpClient) {}

  create(usuarioPerfil: IUsuarioPerfil): Observable<EntityResponseType> {
    return this.http.post<IUsuarioPerfil>(this.resourceUrl, usuarioPerfil, { observe: 'response' });
  }

  update(usuarioPerfil: IUsuarioPerfil): Observable<EntityResponseType> {
    return this.http.put<IUsuarioPerfil>(this.resourceUrl, usuarioPerfil, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUsuarioPerfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUsuarioPerfil[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
