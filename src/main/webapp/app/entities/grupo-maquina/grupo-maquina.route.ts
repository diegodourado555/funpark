import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGrupoMaquina, GrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from './grupo-maquina.service';
import { GrupoMaquinaComponent } from './grupo-maquina.component';
import { GrupoMaquinaDetailComponent } from './grupo-maquina-detail.component';
import { GrupoMaquinaUpdateComponent } from './grupo-maquina-update.component';

@Injectable({ providedIn: 'root' })
export class GrupoMaquinaResolve implements Resolve<IGrupoMaquina> {
  constructor(private service: GrupoMaquinaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGrupoMaquina> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((grupoMaquina: HttpResponse<GrupoMaquina>) => {
          if (grupoMaquina.body) {
            return of(grupoMaquina.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
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
      authorities: ['ROLE_GRUPO_MAQUINA'],
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
      authorities: ['ROLE_GRUPO_MAQUINA_VIEW'],
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
      authorities: ['ROLE_GRUPO_MAQUINA_NEW'],
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
      authorities: ['ROLE_GRUPO_MAQUINA_EDIT'],
      pageTitle: 'funparkApp.grupoMaquina.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
