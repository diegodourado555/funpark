import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMaquina, Maquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';
import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from 'app/entities/grupo-maquina/grupo-maquina.service';

@Component({
  selector: 'jhi-maquina-update',
  templateUrl: './maquina-update.component.html'
})
export class MaquinaUpdateComponent implements OnInit {
  isSaving = false;

  grupomaquinas: IGrupoMaquina[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [],
    grupoMaquinaId: []
  });

  constructor(
    protected maquinaService: MaquinaService,
    protected grupoMaquinaService: GrupoMaquinaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maquina }) => {
      this.updateForm(maquina);

      this.grupoMaquinaService
        .query()
        .pipe(
          map((res: HttpResponse<IGrupoMaquina[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IGrupoMaquina[]) => (this.grupomaquinas = resBody));
    });
  }

  updateForm(maquina: IMaquina): void {
    this.editForm.patchValue({
      id: maquina.id,
      nome: maquina.nome,
      grupoMaquinaId: maquina.grupoMaquinaId
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
      nome: this.editForm.get(['nome'])!.value,
      grupoMaquinaId: this.editForm.get(['grupoMaquinaId'])!.value
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

  trackById(index: number, item: IGrupoMaquina): any {
    return item.id;
  }
}
