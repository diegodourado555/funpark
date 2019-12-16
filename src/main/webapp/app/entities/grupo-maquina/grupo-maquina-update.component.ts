import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGrupoMaquina, GrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from './grupo-maquina.service';

@Component({
  selector: 'jhi-grupo-maquina-update',
  templateUrl: './grupo-maquina-update.component.html'
})
export class GrupoMaquinaUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nome: []
  });

  constructor(protected grupoMaquinaService: GrupoMaquinaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ grupoMaquina }) => {
      this.updateForm(grupoMaquina);
    });
  }

  updateForm(grupoMaquina: IGrupoMaquina) {
    this.editForm.patchValue({
      id: grupoMaquina.id,
      nome: grupoMaquina.nome
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const grupoMaquina = this.createFromForm();
    if (grupoMaquina.id !== undefined) {
      this.subscribeToSaveResponse(this.grupoMaquinaService.update(grupoMaquina));
    } else {
      this.subscribeToSaveResponse(this.grupoMaquinaService.create(grupoMaquina));
    }
  }

  private createFromForm(): IGrupoMaquina {
    return {
      ...new GrupoMaquina(),
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGrupoMaquina>>) {
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
