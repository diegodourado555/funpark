import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IContaCorrente, ContaCorrente } from 'app/shared/model/conta-corrente.model';
import { ContaCorrenteService } from './conta-corrente.service';
import { IReceitas } from 'app/shared/model/receitas.model';
import { ReceitasService } from 'app/entities/receitas/receitas.service';
import { IDespesas } from 'app/shared/model/despesas.model';
import { DespesasService } from 'app/entities/despesas/despesas.service';
import { IOperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from 'app/entities/operador-caixa/operador-caixa.service';
import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from 'app/entities/loja/loja.service';

@Component({
  selector: 'jhi-conta-corrente-update',
  templateUrl: './conta-corrente-update.component.html'
})
export class ContaCorrenteUpdateComponent implements OnInit {
  isSaving: boolean;

  receitas: IReceitas[];

  despesas: IDespesas[];

  operadorcaixas: IOperadorCaixa[];

  lojas: ILoja[];

  editForm = this.fb.group({
    id: [],
    idReceita: [],
    idDespesa: [],
    idOperador: [],
    idLoja: [],
    valor: [],
    data: [],
    metodoPagamento: [],
    receitaId: [],
    despesaId: [],
    operadorCaixaId: [],
    lojaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected contaCorrenteService: ContaCorrenteService,
    protected receitasService: ReceitasService,
    protected despesasService: DespesasService,
    protected operadorCaixaService: OperadorCaixaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ contaCorrente }) => {
      this.updateForm(contaCorrente);
    });
    this.receitasService
      .query()
      .subscribe((res: HttpResponse<IReceitas[]>) => (this.receitas = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.despesasService
      .query()
      .subscribe((res: HttpResponse<IDespesas[]>) => (this.despesas = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.operadorCaixaService
      .query()
      .subscribe(
        (res: HttpResponse<IOperadorCaixa[]>) => (this.operadorcaixas = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.lojaService
      .query()
      .subscribe((res: HttpResponse<ILoja[]>) => (this.lojas = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(contaCorrente: IContaCorrente) {
    this.editForm.patchValue({
      id: contaCorrente.id,
      idReceita: contaCorrente.idReceita,
      idDespesa: contaCorrente.idDespesa,
      idOperador: contaCorrente.idOperador,
      idLoja: contaCorrente.idLoja,
      valor: contaCorrente.valor,
      data: contaCorrente.data != null ? contaCorrente.data.format(DATE_TIME_FORMAT) : null,
      metodoPagamento: contaCorrente.metodoPagamento,
      receitaId: contaCorrente.receitaId,
      despesaId: contaCorrente.despesaId,
      operadorCaixaId: contaCorrente.operadorCaixaId,
      lojaId: contaCorrente.lojaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const contaCorrente = this.createFromForm();
    if (contaCorrente.id !== undefined) {
      this.subscribeToSaveResponse(this.contaCorrenteService.update(contaCorrente));
    } else {
      this.subscribeToSaveResponse(this.contaCorrenteService.create(contaCorrente));
    }
  }

  private createFromForm(): IContaCorrente {
    return {
      ...new ContaCorrente(),
      id: this.editForm.get(['id']).value,
      idReceita: this.editForm.get(['idReceita']).value,
      idDespesa: this.editForm.get(['idDespesa']).value,
      idOperador: this.editForm.get(['idOperador']).value,
      idLoja: this.editForm.get(['idLoja']).value,
      valor: this.editForm.get(['valor']).value,
      data: this.editForm.get(['data']).value != null ? moment(this.editForm.get(['data']).value, DATE_TIME_FORMAT) : undefined,
      metodoPagamento: this.editForm.get(['metodoPagamento']).value,
      receitaId: this.editForm.get(['receitaId']).value,
      despesaId: this.editForm.get(['despesaId']).value,
      operadorCaixaId: this.editForm.get(['operadorCaixaId']).value,
      lojaId: this.editForm.get(['lojaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContaCorrente>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackReceitasById(index: number, item: IReceitas) {
    return item.id;
  }

  trackDespesasById(index: number, item: IDespesas) {
    return item.id;
  }

  trackOperadorCaixaById(index: number, item: IOperadorCaixa) {
    return item.id;
  }

  trackLojaById(index: number, item: ILoja) {
    return item.id;
  }
}
