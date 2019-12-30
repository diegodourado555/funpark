import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IGrupoMaquina, GrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from './grupo-maquina.service';
import { IMaquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from 'app/entities/maquina/maquina.service';

@Component({
  selector: 'jhi-grupo-maquina-update',
  templateUrl: './grupo-maquina-update.component.html'
})
export class GrupoMaquinaUpdateComponent implements OnInit {
  isSaving = false;

  maquinas: IMaquina[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [],
    maquinaId: []
  });

  constructor(
    protected grupoMaquinaService: GrupoMaquinaService,
    protected maquinaService: MaquinaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ grupoMaquina }) => {
      this.updateForm(grupoMaquina);

      this.maquinaService
        .query()
        .pipe(
          map((res: HttpResponse<IMaquina[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IMaquina[]) => (this.maquinas = resBody));
    });
  }

  updateForm(grupoMaquina: IGrupoMaquina): void {
    this.editForm.patchValue({
      id: grupoMaquina.id,
      nome: grupoMaquina.nome,
      maquinaId: grupoMaquina.maquinaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      maquinaId: this.editForm.get(['maquinaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGrupoMaquina>>): void {
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

  trackById(index: number, item: IMaquina): any {
    return item.id;
  }
}
