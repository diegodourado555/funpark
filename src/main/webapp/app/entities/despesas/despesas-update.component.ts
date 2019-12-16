import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
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
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    descricao: []
  });

  constructor(protected despesasService: DespesasService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ despesas }) => {
      this.updateForm(despesas);
    });
  }

  updateForm(despesas: IDespesas) {
    this.editForm.patchValue({
      id: despesas.id,
      descricao: despesas.descricao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDespesas>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
