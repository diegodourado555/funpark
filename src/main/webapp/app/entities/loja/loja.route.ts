import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Loja } from 'app/shared/model/loja.model';
import { LojaService } from './loja.service';
import { LojaComponent } from './loja.component';
import { LojaDetailComponent } from './loja-detail.component';
import { LojaUpdateComponent } from './loja-update.component';
import { ILoja } from 'app/shared/model/loja.model';

@Injectable({ providedIn: 'root' })
export class LojaResolve implements Resolve<ILoja> {
  constructor(private service: LojaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILoja> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((loja: HttpResponse<Loja>) => loja.body));
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.loja.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
