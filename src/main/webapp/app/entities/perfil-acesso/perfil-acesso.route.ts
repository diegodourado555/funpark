import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PerfilAcesso } from 'app/shared/model/perfil-acesso.model';
import { PerfilAcessoService } from './perfil-acesso.service';
import { PerfilAcessoComponent } from './perfil-acesso.component';
import { PerfilAcessoDetailComponent } from './perfil-acesso-detail.component';
import { PerfilAcessoUpdateComponent } from './perfil-acesso-update.component';
import { IPerfilAcesso } from 'app/shared/model/perfil-acesso.model';

@Injectable({ providedIn: 'root' })
export class PerfilAcessoResolve implements Resolve<IPerfilAcesso> {
  constructor(private service: PerfilAcessoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPerfilAcesso> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((perfilAcesso: HttpResponse<PerfilAcesso>) => perfilAcesso.body));
    }
    return of(new PerfilAcesso());
  }
}

export const perfilAcessoRoute: Routes = [
  {
    path: '',
    component: PerfilAcessoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.perfilAcesso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PerfilAcessoDetailComponent,
    resolve: {
      perfilAcesso: PerfilAcessoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.perfilAcesso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PerfilAcessoUpdateComponent,
    resolve: {
      perfilAcesso: PerfilAcessoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.perfilAcesso.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PerfilAcessoUpdateComponent,
    resolve: {
      perfilAcesso: PerfilAcessoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.perfilAcesso.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
