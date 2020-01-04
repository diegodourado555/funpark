import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IHistoricoOperadorCaixa, HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';
import { HistoricoOperadorCaixaService } from './historico-operador-caixa.service';

@Component({
  selector: 'jhi-historico-operador-caixa-update',
  templateUrl: './historico-operador-caixa-update.component.html'
})
export class HistoricoOperadorCaixaUpdateComponent implements OnInit {
  isSaving = false;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    nome: [],
    cpf: [],
    data: [],
    situacao: []
  });

  constructor(
    protected historicoOperadorCaixaService: HistoricoOperadorCaixaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoOperadorCaixa }) => {
      this.updateForm(historicoOperadorCaixa);
    });
  }

  updateForm(historicoOperadorCaixa: IHistoricoOperadorCaixa): void {
    this.editForm.patchValue({
      id: historicoOperadorCaixa.id,
      nome: historicoOperadorCaixa.nome,
      cpf: historicoOperadorCaixa.cpf,
      data: historicoOperadorCaixa.data,
      situacao: historicoOperadorCaixa.situacao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historicoOperadorCaixa = this.createFromForm();
    if (historicoOperadorCaixa.id !== undefined) {
      this.subscribeToSaveResponse(this.historicoOperadorCaixaService.update(historicoOperadorCaixa));
    } else {
      this.subscribeToSaveResponse(this.historicoOperadorCaixaService.create(historicoOperadorCaixa));
    }
  }

  private createFromForm(): IHistoricoOperadorCaixa {
    return {
      ...new HistoricoOperadorCaixa(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      data: this.editForm.get(['data'])!.value,
      situacao: this.editForm.get(['situacao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoricoOperadorCaixa>>): void {
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
}
