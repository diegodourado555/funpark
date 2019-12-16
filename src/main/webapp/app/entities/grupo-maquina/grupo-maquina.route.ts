import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from './grupo-maquina.service';
import { GrupoMaquinaComponent } from './grupo-maquina.component';
import { GrupoMaquinaDetailComponent } from './grupo-maquina-detail.component';
import { GrupoMaquinaUpdateComponent } from './grupo-maquina-update.component';
import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';

@Injectable({ providedIn: 'root' })
export class GrupoMaquinaResolve implements Resolve<IGrupoMaquina> {
  constructor(private service: GrupoMaquinaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGrupoMaquina> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((grupoMaquina: HttpResponse<GrupoMaquina>) => grupoMaquina.body));
    }
    return of(new GrupoMaquina());
  }
}

export const grupoMaquinaRoute: Routes = [
  {
    path: '',
    component: GrupoMaquinaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.grupoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GrupoMaquinaDetailComponent,
    resolve: {
      grupoMaquina: GrupoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.grupoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GrupoMaquinaUpdateComponent,
    resolve: {
      grupoMaquina: GrupoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.grupoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GrupoMaquinaUpdateComponent,
    resolve: {
      grupoMaquina: GrupoMaquinaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.grupoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
