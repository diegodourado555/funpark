import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, Validators } from '@angular/forms';
import { IContaCorrente } from 'app/shared/model/conta-corrente.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ContaCorrenteService } from './conta-corrente.service';
import { ContaCorrenteDeleteDialogComponent } from './conta-corrente-delete-dialog.component';

@Component({
  selector: 'jhi-conta-corrente',
  templateUrl: './conta-corrente.component.html'
})
export class ContaCorrenteComponent implements OnInit, OnDestroy {
  searchForm = this.fb.group({
    descricaoFilter: []
  });
  descricaoFilter: string;
  contaCorrentes?: IContaCorrente[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected contaCorrenteService: ContaCorrenteService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    private fb: FormBuilder
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.contaCorrenteService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IContaCorrente[]>) => this.onSuccess(res.body, res.headers, pageToLoad), () => this.onError());
  }

  ngOnInit(): void {
    this.descricaoFilter = '';
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInContaCorrentes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IContaCorrente): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInContaCorrentes(): void {
    this.eventSubscriber = this.eventManager.subscribe('contaCorrenteListModification', () => this.loadPage());
  }

  delete(contaCorrente: IContaCorrente): void {
    const modalRef = this.modalService.open(ContaCorrenteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.contaCorrente = contaCorrente;
  }

  search() {
    this.contaCorrenteService.search(this.searchForm.get(['descricaoFilter'])!.value).subscribe(x => {
      this.contaCorrentes = x.body;
    });
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IContaCorrente[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/conta-corrente'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.contaCorrentes = data ? data : [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
