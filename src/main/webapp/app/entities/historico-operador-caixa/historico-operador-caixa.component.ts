import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HistoricoOperadorCaixaService } from './historico-operador-caixa.service';
import { HistoricoOperadorCaixaDeleteDialogComponent } from './historico-operador-caixa-delete-dialog.component';

@Component({
  selector: 'jhi-historico-operador-caixa',
  templateUrl: './historico-operador-caixa.component.html'
})
export class HistoricoOperadorCaixaComponent implements OnInit, OnDestroy {
  historicoOperadorCaixas?: IHistoricoOperadorCaixa[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected historicoOperadorCaixaService: HistoricoOperadorCaixaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.historicoOperadorCaixaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IHistoricoOperadorCaixa[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInHistoricoOperadorCaixas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IHistoricoOperadorCaixa): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInHistoricoOperadorCaixas(): void {
    this.eventSubscriber = this.eventManager.subscribe('historicoOperadorCaixaListModification', () => this.loadPage());
  }

  delete(historicoOperadorCaixa: IHistoricoOperadorCaixa): void {
    const modalRef = this.modalService.open(HistoricoOperadorCaixaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.historicoOperadorCaixa = historicoOperadorCaixa;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IHistoricoOperadorCaixa[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/historico-operador-caixa'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.historicoOperadorCaixas = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
