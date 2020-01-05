import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOperadorCaixa, OperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from './operador-caixa.service';
import { OperadorCaixaComponent } from './operador-caixa.component';
import { OperadorCaixaDetailComponent } from './operador-caixa-detail.component';
import { OperadorCaixaUpdateComponent } from './operador-caixa-update.component';

@Injectable({ providedIn: 'root' })
export class OperadorCaixaResolve implements Resolve<IOperadorCaixa> {
  constructor(private service: OperadorCaixaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOperadorCaixa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((operadorCaixa: HttpResponse<OperadorCaixa>) => {
          if (operadorCaixa.body) {
            return of(operadorCaixa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
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
      authorities: ['ROLE_OPERADOR_CAIXA'],
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
      authorities: ['ROLE_OPERADOR_CAIXA_VIEW'],
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
      authorities: ['ROLE_OPERADOR_CAIXA_NEW'],
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
      authorities: ['ROLE_OPERADOR_CAIXA_EDIT'],
      pageTitle: 'funparkApp.operadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
