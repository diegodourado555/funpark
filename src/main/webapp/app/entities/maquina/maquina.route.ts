import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Maquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';
import { MaquinaComponent } from './maquina.component';
import { MaquinaDetailComponent } from './maquina-detail.component';
import { MaquinaUpdateComponent } from './maquina-update.component';
import { IMaquina } from 'app/shared/model/maquina.model';

@Injectable({ providedIn: 'root' })
export class MaquinaResolve implements Resolve<IMaquina> {
  constructor(private service: MaquinaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMaquina> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((maquina: HttpResponse<Maquina>) => maquina.body));
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
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
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.maquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
