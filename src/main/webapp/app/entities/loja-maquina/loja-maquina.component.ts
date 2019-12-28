import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILojaMaquina } from 'app/shared/model/loja-maquina.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { LojaMaquinaService } from './loja-maquina.service';
import { LojaMaquinaDeleteDialogComponent } from './loja-maquina-delete-dialog.component';

@Component({
  selector: 'jhi-loja-maquina',
  templateUrl: './loja-maquina.component.html'
})
export class LojaMaquinaComponent implements OnInit, OnDestroy {
  lojaMaquinas?: ILojaMaquina[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected lojaMaquinaService: LojaMaquinaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.lojaMaquinaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<ILojaMaquina[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInLojaMaquinas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILojaMaquina): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLojaMaquinas(): void {
    this.eventSubscriber = this.eventManager.subscribe('lojaMaquinaListModification', () => this.loadPage());
  }

  delete(lojaMaquina: ILojaMaquina): void {
    const modalRef = this.modalService.open(LojaMaquinaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lojaMaquina = lojaMaquina;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: ILojaMaquina[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/loja-maquina'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.lojaMaquinas = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
