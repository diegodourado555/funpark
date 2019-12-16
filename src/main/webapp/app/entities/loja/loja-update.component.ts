import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ILoja, Loja } from 'app/shared/model/loja.model';
import { LojaService } from './loja.service';

@Component({
  selector: 'jhi-loja-update',
  templateUrl: './loja-update.component.html'
})
export class LojaUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nomeFantasia: [],
    razaoSocial: [],
    cNPJ: [],
    endereco: []
  });

  constructor(protected lojaService: LojaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ loja }) => {
      this.updateForm(loja);
    });
  }

  updateForm(loja: ILoja) {
    this.editForm.patchValue({
      id: loja.id,
      nomeFantasia: loja.nomeFantasia,
      razaoSocial: loja.razaoSocial,
      cNPJ: loja.cNPJ,
      endereco: loja.endereco
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const loja = this.createFromForm();
    if (loja.id !== undefined) {
      this.subscribeToSaveResponse(this.lojaService.update(loja));
    } else {
      this.subscribeToSaveResponse(this.lojaService.create(loja));
    }
  }

  private createFromForm(): ILoja {
    return {
      ...new Loja(),
      id: this.editForm.get(['id']).value,
      nomeFantasia: this.editForm.get(['nomeFantasia']).value,
      razaoSocial: this.editForm.get(['razaoSocial']).value,
      cNPJ: this.editForm.get(['cNPJ']).value,
      endereco: this.editForm.get(['endereco']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILoja>>) {
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
