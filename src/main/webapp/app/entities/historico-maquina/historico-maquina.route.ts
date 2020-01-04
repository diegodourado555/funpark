import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHistoricoMaquina, HistoricoMaquina } from 'app/shared/model/historico-maquina.model';
import { HistoricoMaquinaService } from './historico-maquina.service';
import { HistoricoMaquinaComponent } from './historico-maquina.component';
import { HistoricoMaquinaDetailComponent } from './historico-maquina-detail.component';
import { HistoricoMaquinaUpdateComponent } from './historico-maquina-update.component';

@Injectable({ providedIn: 'root' })
export class HistoricoMaquinaResolve implements Resolve<IHistoricoMaquina> {
  constructor(private service: HistoricoMaquinaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHistoricoMaquina> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((historicoMaquina: HttpResponse<HistoricoMaquina>) => {
          if (historicoMaquina.body) {
            return of(historicoMaquina.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HistoricoMaquina());
  }
}

export const historicoMaquinaRoute: Routes = [
  {
    path: '',
    component: HistoricoMaquinaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.historicoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HistoricoMaquinaDetailComponent,
    resolve: {
      historicoMaquina: HistoricoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HistoricoMaquinaUpdateComponent,
    resolve: {
      historicoMaquina: HistoricoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HistoricoMaquinaUpdateComponent,
    resolve: {
      historicoMaquina: HistoricoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.historicoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
