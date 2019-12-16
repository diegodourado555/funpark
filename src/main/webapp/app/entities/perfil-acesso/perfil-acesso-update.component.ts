import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPerfilAcesso, PerfilAcesso } from 'app/shared/model/perfil-acesso.model';
import { PerfilAcessoService } from './perfil-acesso.service';

@Component({
  selector: 'jhi-perfil-acesso-update',
  templateUrl: './perfil-acesso-update.component.html'
})
export class PerfilAcessoUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    descricao: []
  });

  constructor(protected perfilAcessoService: PerfilAcessoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ perfilAcesso }) => {
      this.updateForm(perfilAcesso);
    });
  }

  updateForm(perfilAcesso: IPerfilAcesso) {
    this.editForm.patchValue({
      id: perfilAcesso.id,
      descricao: perfilAcesso.descricao
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const perfilAcesso = this.createFromForm();
    if (perfilAcesso.id !== undefined) {
      this.subscribeToSaveResponse(this.perfilAcessoService.update(perfilAcesso));
    } else {
      this.subscribeToSaveResponse(this.perfilAcessoService.create(perfilAcesso));
    }
  }

  private createFromForm(): IPerfilAcesso {
    return {
      ...new PerfilAcesso(),
      id: this.editForm.get(['id']).value,
      descricao: this.editForm.get(['descricao']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfilAcesso>>) {
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
