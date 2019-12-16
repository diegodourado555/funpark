import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MenuPerfil } from 'app/shared/model/menu-perfil.model';
import { MenuPerfilService } from './menu-perfil.service';
import { MenuPerfilComponent } from './menu-perfil.component';
import { MenuPerfilDetailComponent } from './menu-perfil-detail.component';
import { MenuPerfilUpdateComponent } from './menu-perfil-update.component';
import { IMenuPerfil } from 'app/shared/model/menu-perfil.model';

@Injectable({ providedIn: 'root' })
export class MenuPerfilResolve implements Resolve<IMenuPerfil> {
  constructor(private service: MenuPerfilService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMenuPerfil> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((menuPerfil: HttpResponse<MenuPerfil>) => menuPerfil.body));
    }
    return of(new MenuPerfil());
  }
}

export const menuPerfilRoute: Routes = [
  {
    path: '',
    component: MenuPerfilComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.menuPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MenuPerfilDetailComponent,
    resolve: {
      menuPerfil: MenuPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.menuPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MenuPerfilUpdateComponent,
    resolve: {
      menuPerfil: MenuPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.menuPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MenuPerfilUpdateComponent,
    resolve: {
      menuPerfil: MenuPerfilResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.menuPerfil.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
