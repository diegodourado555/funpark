import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Funpark } from 'app/shared/model/funpark.model';
import { FunparkService } from './funpark.service';
import { FunparkComponent } from './funpark.component';
import { FunparkDetailComponent } from './funpark-detail.component';
import { FunparkUpdateComponent } from './funpark-update.component';
import { IFunpark } from 'app/shared/model/funpark.model';

@Injectable({ providedIn: 'root' })
export class FunparkResolve implements Resolve<IFunpark> {
  constructor(private service: FunparkService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFunpark> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((funpark: HttpResponse<Funpark>) => funpark.body));
    }
    return of(new Funpark());
  }
}

export const funparkRoute: Routes = [
  {
    path: '',
    component: FunparkComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.funpark.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FunparkDetailComponent,
    resolve: {
      funpark: FunparkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.funpark.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FunparkUpdateComponent,
    resolve: {
      funpark: FunparkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.funpark.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FunparkUpdateComponent,
    resolve: {
      funpark: FunparkResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.funpark.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
