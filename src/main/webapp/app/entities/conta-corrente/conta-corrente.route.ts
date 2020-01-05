import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IContaCorrente, ContaCorrente } from 'app/shared/model/conta-corrente.model';
import { ContaCorrenteService } from './conta-corrente.service';
import { ContaCorrenteComponent } from './conta-corrente.component';
import { ContaCorrenteDetailComponent } from './conta-corrente-detail.component';
import { ContaCorrenteUpdateComponent } from './conta-corrente-update.component';

@Injectable({ providedIn: 'root' })
export class ContaCorrenteResolve implements Resolve<IContaCorrente> {
  constructor(private service: ContaCorrenteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IContaCorrente> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((contaCorrente: HttpResponse<ContaCorrente>) => {
          if (contaCorrente.body) {
            return of(contaCorrente.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
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
      authorities: ['ROLE_CONTA_CORRENTE'],
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
      authorities: ['ROLE_CONTA_CORRENTE_VIEW'],
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
      authorities: ['ROLE_CONTA_CORRENTE_NEW'],
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
      authorities: ['ROLE_CONTA_CORRENTE_EDIT'],
      pageTitle: 'funparkApp.contaCorrente.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
