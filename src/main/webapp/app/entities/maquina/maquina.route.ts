import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMaquina, Maquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';
import { MaquinaComponent } from './maquina.component';
import { MaquinaDetailComponent } from './maquina-detail.component';
import { MaquinaUpdateComponent } from './maquina-update.component';

@Injectable({ providedIn: 'root' })
export class MaquinaResolve implements Resolve<IMaquina> {
  constructor(private service: MaquinaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMaquina> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((maquina: HttpResponse<Maquina>) => {
          if (maquina.body) {
            return of(maquina.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Maquina());
  }
}

export const maquinaRoute: Routes = [
  {
    path: '',
    component: MaquinaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_MAQUINA'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.maquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MaquinaDetailComponent,
    resolve: {
      maquina: MaquinaResolve
    },
    data: {
      authorities: ['ROLE_MAQUINA_VIEW'],
      pageTitle: 'funparkApp.maquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MaquinaUpdateComponent,
    resolve: {
      maquina: MaquinaResolve
    },
    data: {
      authorities: ['ROLE_MAQUINA_NEW'],
      pageTitle: 'funparkApp.maquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MaquinaUpdateComponent,
    resolve: {
      maquina: MaquinaResolve
    },
    data: {
      authorities: ['ROLE_MAQUINA_EDIT'],
      pageTitle: 'funparkApp.maquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
