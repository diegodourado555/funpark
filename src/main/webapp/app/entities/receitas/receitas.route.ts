import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Receitas } from 'app/shared/model/receitas.model';
import { ReceitasService } from './receitas.service';
import { ReceitasComponent } from './receitas.component';
import { ReceitasDetailComponent } from './receitas-detail.component';
import { ReceitasUpdateComponent } from './receitas-update.component';
import { IReceitas } from 'app/shared/model/receitas.model';

@Injectable({ providedIn: 'root' })
export class ReceitasResolve implements Resolve<IReceitas> {
  constructor(private service: ReceitasService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReceitas> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((receitas: HttpResponse<Receitas>) => receitas.body));
    }
    return of(new Receitas());
  }
}

export const receitasRoute: Routes = [
  {
    path: '',
    component: ReceitasComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.receitas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ReceitasDetailComponent,
    resolve: {
      receitas: ReceitasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.receitas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ReceitasUpdateComponent,
    resolve: {
      receitas: ReceitasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.receitas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ReceitasUpdateComponent,
    resolve: {
      receitas: ReceitasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.receitas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
