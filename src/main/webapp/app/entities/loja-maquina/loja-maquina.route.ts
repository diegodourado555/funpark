import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LojaMaquina } from 'app/shared/model/loja-maquina.model';
import { LojaMaquinaService } from './loja-maquina.service';
import { LojaMaquinaComponent } from './loja-maquina.component';
import { LojaMaquinaDetailComponent } from './loja-maquina-detail.component';
import { LojaMaquinaUpdateComponent } from './loja-maquina-update.component';
import { ILojaMaquina } from 'app/shared/model/loja-maquina.model';

@Injectable({ providedIn: 'root' })
export class LojaMaquinaResolve implements Resolve<ILojaMaquina> {
  constructor(private service: LojaMaquinaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILojaMaquina> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((lojaMaquina: HttpResponse<LojaMaquina>) => lojaMaquina.body));
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
