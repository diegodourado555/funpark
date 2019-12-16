import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMenuPerfil } from 'app/shared/model/menu-perfil.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { MenuPerfilService } from './menu-perfil.service';
import { MenuPerfilDeleteDialogComponent } from './menu-perfil-delete-dialog.component';

@Component({
  selector: 'jhi-menu-perfil',
  templateUrl: './menu-perfil.component.html'
})
export class MenuPerfilComponent implements OnInit, OnDestroy {
  menuPerfils: IMenuPerfil[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected menuPerfilService: MenuPerfilService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.menuPerfilService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IMenuPerfil[]>) => this.paginateMenuPerfils(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/menu-perfil'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/menu-perfil',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInMenuPerfils();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMenuPerfil) {
    return item.id;
  }

  registerChangeInMenuPerfils() {
    this.eventSubscriber = this.eventManager.subscribe('menuPerfilListModification', () => this.loadAll());
  }

  delete(menuPerfil: IMenuPerfil) {
    const modalRef = this.modalService.open(MenuPerfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.menuPerfil = menuPerfil;
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateMenuPerfils(data: IMenuPerfil[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.menuPerfils = data;
  }
}
