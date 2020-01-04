import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

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

type SelectableEntity = IReceitas | IDespesas | IOperadorCaixa | ILoja;

@Component({
  selector: 'jhi-conta-corrente-update',
  templateUrl: './conta-corrente-update.component.html'
})
export class ContaCorrenteUpdateComponent implements OnInit {
  isSaving = false;

  receitas: IReceitas[] = [];

  despesas: IDespesas[] = [];

  operadorcaixas: IOperadorCaixa[] = [];

  lojas: ILoja[] = [];

  editForm = this.fb.group({
    id: [],
    valor: [],
    data: [],
    descricao: [],
    metodoPagamento: [],
    situacao: [],
    receitaId: [],
    despesaId: [],
    operadorCaixaId: [],
    lojaId: []
  });

  constructor(
    protected contaCorrenteService: ContaCorrenteService,
    protected receitasService: ReceitasService,
    protected despesasService: DespesasService,
    protected operadorCaixaService: OperadorCaixaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contaCorrente }) => {
      this.updateForm(contaCorrente);

      this.receitasService
        .query()
        .pipe(
          map((res: HttpResponse<IReceitas[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IReceitas[]) => (this.receitas = resBody));

      this.despesasService
        .query()
        .pipe(
          map((res: HttpResponse<IDespesas[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IDespesas[]) => (this.despesas = resBody));

      this.operadorCaixaService
        .query()
        .pipe(
          map((res: HttpResponse<IOperadorCaixa[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IOperadorCaixa[]) => (this.operadorcaixas = resBody));

      this.lojaService
        .query()
        .pipe(
          map((res: HttpResponse<ILoja[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILoja[]) => (this.lojas = resBody));
    });
  }

  updateForm(contaCorrente: IContaCorrente): void {
    this.editForm.patchValue({
      id: contaCorrente.id,
      valor: contaCorrente.valor,
      data: contaCorrente.data != null ? contaCorrente.data.format(DATE_TIME_FORMAT) : null,
      descricao: contaCorrente.descricao,
      metodoPagamento: contaCorrente.metodoPagamento,
      situacao: contaCorrente.situacao,
      receitaId: contaCorrente.receitaId,
      despesaId: contaCorrente.despesaId,
      operadorCaixaId: contaCorrente.operadorCaixaId,
      lojaId: contaCorrente.lojaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      valor: this.editForm.get(['valor'])!.value,
      data: this.editForm.get(['data'])!.value != null ? moment(this.editForm.get(['data'])!.value, DATE_TIME_FORMAT) : undefined,
      descricao: this.editForm.get(['descricao'])!.value,
      metodoPagamento: this.editForm.get(['metodoPagamento'])!.value,
      situacao: this.editForm.get(['situacao'])!.value,
      receitaId: this.editForm.get(['receitaId'])!.value,
      despesaId: this.editForm.get(['despesaId'])!.value,
      operadorCaixaId: this.editForm.get(['operadorCaixaId'])!.value,
      lojaId: this.editForm.get(['lojaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContaCorrente>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
