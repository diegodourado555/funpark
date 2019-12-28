import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDespesas, Despesas } from 'app/shared/model/despesas.model';
import { DespesasService } from './despesas.service';

@Component({
  selector: 'jhi-despesas-update',
  templateUrl: './despesas-update.component.html'
})
export class DespesasUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descricao: []
  });

  constructor(protected despesasService: DespesasService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ despesas }) => {
      this.updateForm(despesas);
    });
  }

  updateForm(despesas: IDespesas): void {
    this.editForm.patchValue({
      id: despesas.id,
      descricao: despesas.descricao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const despesas = this.createFromForm();
    if (despesas.id !== undefined) {
      this.subscribeToSaveResponse(this.despesasService.update(despesas));
    } else {
      this.subscribeToSaveResponse(this.despesasService.create(despesas));
    }
  }

  private createFromForm(): IDespesas {
    return {
      ...new Despesas(),
      id: this.editForm.get(['id'])!.value,
      descricao: this.editForm.get(['descricao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDespesas>>): void {
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
