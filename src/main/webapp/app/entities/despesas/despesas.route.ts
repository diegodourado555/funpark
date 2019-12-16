import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Despesas } from 'app/shared/model/despesas.model';
import { DespesasService } from './despesas.service';
import { DespesasComponent } from './despesas.component';
import { DespesasDetailComponent } from './despesas-detail.component';
import { DespesasUpdateComponent } from './despesas-update.component';
import { IDespesas } from 'app/shared/model/despesas.model';

@Injectable({ providedIn: 'root' })
export class DespesasResolve implements Resolve<IDespesas> {
  constructor(private service: DespesasService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDespesas> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((despesas: HttpResponse<Despesas>) => despesas.body));
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
