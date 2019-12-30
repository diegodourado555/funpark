import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMaquina, Maquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';

@Component({
  selector: 'jhi-maquina-update',
  templateUrl: './maquina-update.component.html'
})
export class MaquinaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nome: []
  });

  constructor(protected maquinaService: MaquinaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maquina }) => {
      this.updateForm(maquina);
    });
  }

  updateForm(maquina: IMaquina): void {
    this.editForm.patchValue({
      id: maquina.id,
      nome: maquina.nome
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const maquina = this.createFromForm();
    if (maquina.id !== undefined) {
      this.subscribeToSaveResponse(this.maquinaService.update(maquina));
    } else {
      this.subscribeToSaveResponse(this.maquinaService.create(maquina));
    }
  }

  private createFromForm(): IMaquina {
    return {
      ...new Maquina(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaquina>>): void {
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
