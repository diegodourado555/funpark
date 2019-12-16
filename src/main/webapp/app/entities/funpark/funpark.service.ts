import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFunpark } from 'app/shared/model/funpark.model';

type EntityResponseType = HttpResponse<IFunpark>;
type EntityArrayResponseType = HttpResponse<IFunpark[]>;

@Injectable({ providedIn: 'root' })
export class FunparkService {
  public resourceUrl = SERVER_API_URL + 'api/funparks';

  constructor(protected http: HttpClient) {}

  create(funpark: IFunpark): Observable<EntityResponseType> {
    return this.http.post<IFunpark>(this.resourceUrl, funpark, { observe: 'response' });
  }

  update(funpark: IFunpark): Observable<EntityResponseType> {
    return this.http.put<IFunpark>(this.resourceUrl, funpark, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFunpark>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFunpark[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
