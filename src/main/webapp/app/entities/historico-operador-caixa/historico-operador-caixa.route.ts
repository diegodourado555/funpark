import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHistoricoOperadorCaixa, HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';
import { HistoricoOperadorCaixaService } from './historico-operador-caixa.service';
import { HistoricoOperadorCaixaComponent } from './historico-operador-caixa.component';
import { HistoricoOperadorCaixaDetailComponent } from './historico-operador-caixa-detail.component';
import { HistoricoOperadorCaixaUpdateComponent } from './historico-operador-caixa-update.component';

@Injectable({ providedIn: 'root' })
export class HistoricoOperadorCaixaResolve implements Resolve<IHistoricoOperadorCaixa> {
  constructor(private service: HistoricoOperadorCaixaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistoricoOperadorCaixa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((historicoOperadorCaixa: HttpResponse<HistoricoOperadorCaixa>) => {
          if (historicoOperadorCaixa.body) {
            return of(historicoOperadorCaixa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HistoricoOperadorCaixa());
  }
}

export const historicoOperadorCaixaRoute: Routes = [
  {
    path: '',
    component: HistoricoOperadorCaixaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.historicoOperadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HistoricoOperadorCaixaDetailComponent,
    resolve: {
      historicoOperadorCaixa: HistoricoOperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoOperadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HistoricoOperadorCaixaUpdateComponent,
    resolve: {
      historicoOperadorCaixa: HistoricoOperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoOperadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HistoricoOperadorCaixaUpdateComponent,
    resolve: {
      historicoOperadorCaixa: HistoricoOperadorCaixaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoOperadorCaixa.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
