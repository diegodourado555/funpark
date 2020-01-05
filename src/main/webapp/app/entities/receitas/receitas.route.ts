import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReceitas, Receitas } from 'app/shared/model/receitas.model';
import { ReceitasService } from './receitas.service';
import { ReceitasComponent } from './receitas.component';
import { ReceitasDetailComponent } from './receitas-detail.component';
import { ReceitasUpdateComponent } from './receitas-update.component';

@Injectable({ providedIn: 'root' })
export class ReceitasResolve implements Resolve<IReceitas> {
  constructor(private service: ReceitasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReceitas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((receitas: HttpResponse<Receitas>) => {
          if (receitas.body) {
            return of(receitas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
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
      authorities: ['ROLE_RECEITAS'],
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
      authorities: ['ROLE_RECEITAS_VIEW'],
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
      authorities: ['ROLE_RECEITAS_NEW'],
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
      authorities: ['ROLE_RECEITAS_EDIT'],
      pageTitle: 'funparkApp.receitas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
