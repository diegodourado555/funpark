import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IHistoricoMaquina, HistoricoMaquina } from 'app/shared/model/historico-maquina.model';
import { HistoricoMaquinaService } from './historico-maquina.service';

@Component({
  selector: 'jhi-historico-maquina-update',
  templateUrl: './historico-maquina-update.component.html'
})
export class HistoricoMaquinaUpdateComponent implements OnInit {
  isSaving = false;
  dataDp: any;

  editForm = this.fb.group({
    id: [],
    nome: [],
    data: [],
    situacao: []
  });

  constructor(
    protected historicoMaquinaService: HistoricoMaquinaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoMaquina }) => {
      this.updateForm(historicoMaquina);
    });
  }

  updateForm(historicoMaquina: IHistoricoMaquina): void {
    this.editForm.patchValue({
      id: historicoMaquina.id,
      nome: historicoMaquina.nome,
      data: historicoMaquina.data,
      situacao: historicoMaquina.situacao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const historicoMaquina = this.createFromForm();
    if (historicoMaquina.id !== undefined) {
      this.subscribeToSaveResponse(this.historicoMaquinaService.update(historicoMaquina));
    } else {
      this.subscribeToSaveResponse(this.historicoMaquinaService.create(historicoMaquina));
    }
  }

  private createFromForm(): IHistoricoMaquina {
    return {
      ...new HistoricoMaquina(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      data: this.editForm.get(['data'])!.value,
      situacao: this.editForm.get(['situacao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHistoricoMaquina>>): void {
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
