import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILoja, Loja } from 'app/shared/model/loja.model';
import { LojaService } from './loja.service';
import { LojaComponent } from './loja.component';
import { LojaDetailComponent } from './loja-detail.component';
import { LojaUpdateComponent } from './loja-update.component';

@Injectable({ providedIn: 'root' })
export class LojaResolve implements Resolve<ILoja> {
  constructor(private service: LojaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILoja> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((loja: HttpResponse<Loja>) => {
          if (loja.body) {
            return of(loja.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Loja());
  }
}

export const lojaRoute: Routes = [
  {
    path: '',
    component: LojaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_LOJA'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.loja.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LojaDetailComponent,
    resolve: {
      loja: LojaResolve
    },
    data: {
      authorities: ['ROLE_LOJA_VIEW'],
      pageTitle: 'funparkApp.loja.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LojaUpdateComponent,
    resolve: {
      loja: LojaResolve
    },
    data: {
      authorities: ['ROLE_LOJA_NEW'],
      pageTitle: 'funparkApp.loja.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LojaUpdateComponent,
    resolve: {
      loja: LojaResolve
    },
    data: {
      authorities: ['ROLE_LOJA_EDIT'],
      pageTitle: 'funparkApp.loja.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
