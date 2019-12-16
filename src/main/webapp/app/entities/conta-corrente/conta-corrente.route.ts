import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ContaCorrente } from 'app/shared/model/conta-corrente.model';
import { ContaCorrenteService } from './conta-corrente.service';
import { ContaCorrenteComponent } from './conta-corrente.component';
import { ContaCorrenteDetailComponent } from './conta-corrente-detail.component';
import { ContaCorrenteUpdateComponent } from './conta-corrente-update.component';
import { IContaCorrente } from 'app/shared/model/conta-corrente.model';

@Injectable({ providedIn: 'root' })
export class ContaCorrenteResolve implements Resolve<IContaCorrente> {
  constructor(private service: ContaCorrenteService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContaCorrente> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((contaCorrente: HttpResponse<ContaCorrente>) => contaCorrente.body));
    }
    return of(new ContaCorrente());
  }
}

export const contaCorrenteRoute: Routes = [
  {
    path: '',
    component: ContaCorrenteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'funparkApp.contaCorrente.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ContaCorrenteDetailComponent,
    resolve: {
      contaCorrente: ContaCorrenteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.contaCorrente.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ContaCorrenteUpdateComponent,
    resolve: {
      contaCorrente: ContaCorrenteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.contaCorrente.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ContaCorrenteUpdateComponent,
    resolve: {
      contaCorrente: ContaCorrenteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'funparkApp.contaCorrente.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
