import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IFunpark, Funpark } from 'app/shared/model/funpark.model';
import { FunparkService } from './funpark.service';

@Component({
  selector: 'jhi-funpark-update',
  templateUrl: './funpark-update.component.html'
})
export class FunparkUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    nome: []
  });

  constructor(protected funparkService: FunparkService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ funpark }) => {
      this.updateForm(funpark);
    });
  }

  updateForm(funpark: IFunpark) {
    this.editForm.patchValue({
      id: funpark.id,
      nome: funpark.nome
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const funpark = this.createFromForm();
    if (funpark.id !== undefined) {
      this.subscribeToSaveResponse(this.funparkService.update(funpark));
    } else {
      this.subscribeToSaveResponse(this.funparkService.create(funpark));
    }
  }

  private createFromForm(): IFunpark {
    return {
      ...new Funpark(),
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFunpark>>) {
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
