import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IReceitas, Receitas } from 'app/shared/model/receitas.model';
import { ReceitasService } from './receitas.service';

@Component({
  selector: 'jhi-receitas-update',
  templateUrl: './receitas-update.component.html'
})
export class ReceitasUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descricao: []
  });

  constructor(protected receitasService: ReceitasService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ receitas }) => {
      this.updateForm(receitas);
    });
  }

  updateForm(receitas: IReceitas): void {
    this.editForm.patchValue({
      id: receitas.id,
      descricao: receitas.descricao
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const receitas = this.createFromForm();
    if (receitas.id !== undefined) {
      this.subscribeToSaveResponse(this.receitasService.update(receitas));
    } else {
      this.subscribeToSaveResponse(this.receitasService.create(receitas));
    }
  }

  private createFromForm(): IReceitas {
    return {
      ...new Receitas(),
      id: this.editForm.get(['id'])!.value,
      descricao: this.editForm.get(['descricao'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReceitas>>): void {
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
