import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReceitas } from 'app/shared/model/receitas.model';

type EntityResponseType = HttpResponse<IReceitas>;
type EntityArrayResponseType = HttpResponse<IReceitas[]>;

@Injectable({ providedIn: 'root' })
export class ReceitasService {
  public resourceUrl = SERVER_API_URL + 'api/receitas';

  constructor(protected http: HttpClient) {}

  create(receitas: IReceitas): Observable<EntityResponseType> {
    return this.http.post<IReceitas>(this.resourceUrl, receitas, { observe: 'response' });
  }

  update(receitas: IReceitas): Observable<EntityResponseType> {
    return this.http.put<IReceitas>(this.resourceUrl, receitas, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IReceitas>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IReceitas[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
