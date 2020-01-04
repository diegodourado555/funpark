import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHistoricoMaquina } from 'app/shared/model/historico-maquina.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HistoricoMaquinaService } from './historico-maquina.service';
import { HistoricoMaquinaDeleteDialogComponent } from './historico-maquina-delete-dialog.component';

@Component({
  selector: 'jhi-historico-maquina',
  templateUrl: './historico-maquina.component.html'
})
export class HistoricoMaquinaComponent implements OnInit, OnDestroy {
  historicoMaquinas?: IHistoricoMaquina[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected historicoMaquinaService: HistoricoMaquinaService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.historicoMaquinaService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IHistoricoMaquina[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInHistoricoMaquinas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IHistoricoMaquina): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInHistoricoMaquinas(): void {
    this.eventSubscriber = this.eventManager.subscribe('historicoMaquinaListModification', () => this.loadPage());
  }

  delete(historicoMaquina: IHistoricoMaquina): void {
    const modalRef = this.modalService.open(HistoricoMaquinaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.historicoMaquina = historicoMaquina;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IHistoricoMaquina[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/historico-maquina'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.historicoMaquinas = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
