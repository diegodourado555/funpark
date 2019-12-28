import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILojaMaquina, LojaMaquina } from 'app/shared/model/loja-maquina.model';
import { LojaMaquinaService } from './loja-maquina.service';
import { LojaMaquinaComponent } from './loja-maquina.component';
import { LojaMaquinaDetailComponent } from './loja-maquina-detail.component';
import { LojaMaquinaUpdateComponent } from './loja-maquina-update.component';

@Injectable({ providedIn: 'root' })
export class LojaMaquinaResolve implements Resolve<ILojaMaquina> {
  constructor(private service: LojaMaquinaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILojaMaquina> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lojaMaquina: HttpResponse<LojaMaquina>) => {
          if (lojaMaquina.body) {
            return of(lojaMaquina.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LojaMaquina());
  }
}

export const lojaMaquinaRoute: Routes = [
  {
    path: '',
    component: LojaMaquinaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.lojaMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LojaMaquinaDetailComponent,
    resolve: {
      lojaMaquina: LojaMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.lojaMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LojaMaquinaUpdateComponent,
    resolve: {
      lojaMaquina: LojaMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.lojaMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LojaMaquinaUpdateComponent,
    resolve: {
      lojaMaquina: LojaMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.lojaMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
