import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { OperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from './operador-caixa.service';
import { OperadorCaixaComponent } from './operador-caixa.component';
import { OperadorCaixaDetailComponent } from './operador-caixa-detail.component';
import { OperadorCaixaUpdateComponent } from './operador-caixa-update.component';
import { IOperadorCaixa } from 'app/shared/model/operador-caixa.model';

@Injectable({ providedIn: 'root' })
export class OperadorCaixaResolve implements Resolve<IOperadorCaixa> {
  constructor(private service: OperadorCaixaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperadorCaixa> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((operadorCaixa: HttpResponse<OperadorCaixa>) => operadorCaixa.body));
    }
    return of(new OperadorCaixa());
  }
}

export const operadorCaixaRoute: Routes = [
  {
    path: '',
    component: OperadorCaixaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.operadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OperadorCaixaDetailComponent,
    resolve: {
      operadorCaixa: OperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.operadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OperadorCaixaUpdateComponent,
    resolve: {
      operadorCaixa: OperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.operadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OperadorCaixaUpdateComponent,
    resolve: {
      operadorCaixa: OperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.operadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
