import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDespesas, Despesas } from 'app/shared/model/despesas.model';
import { DespesasService } from './despesas.service';
import { DespesasComponent } from './despesas.component';
import { DespesasDetailComponent } from './despesas-detail.component';
import { DespesasUpdateComponent } from './despesas-update.component';

@Injectable({ providedIn: 'root' })
export class DespesasResolve implements Resolve<IDespesas> {
  constructor(private service: DespesasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDespesas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((despesas: HttpResponse<Despesas>) => {
          if (despesas.body) {
            return of(despesas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Despesas());
  }
}

export const despesasRoute: Routes = [
  {
    path: '',
    component: DespesasComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.despesas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DespesasDetailComponent,
    resolve: {
      despesas: DespesasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.despesas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DespesasUpdateComponent,
    resolve: {
      despesas: DespesasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.despesas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DespesasUpdateComponent,
    resolve: {
      despesas: DespesasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.despesas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
