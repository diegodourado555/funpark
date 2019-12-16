import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UsuarioPerfil } from 'app/shared/model/usuario-perfil.model';
import { UsuarioPerfilService } from './usuario-perfil.service';
import { UsuarioPerfilComponent } from './usuario-perfil.component';
import { UsuarioPerfilDetailComponent } from './usuario-perfil-detail.component';
import { UsuarioPerfilUpdateComponent } from './usuario-perfil-update.component';
import { IUsuarioPerfil } from 'app/shared/model/usuario-perfil.model';

@Injectable({ providedIn: 'root' })
export class UsuarioPerfilResolve implements Resolve<IUsuarioPerfil> {
  constructor(private service: UsuarioPerfilService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUsuarioPerfil> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((usuarioPerfil: HttpResponse<UsuarioPerfil>) => usuarioPerfil.body));
    }
    return of(new UsuarioPerfil());
  }
}

export const usuarioPerfilRoute: Routes = [
  {
    path: '',
    component: UsuarioPerfilComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.usuarioPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UsuarioPerfilDetailComponent,
    resolve: {
      usuarioPerfil: UsuarioPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.usuarioPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UsuarioPerfilUpdateComponent,
    resolve: {
      usuarioPerfil: UsuarioPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.usuarioPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UsuarioPerfilUpdateComponent,
    resolve: {
      usuarioPerfil: UsuarioPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.usuarioPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
