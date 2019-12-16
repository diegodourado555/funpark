import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IMaquina, Maquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';
import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from 'app/entities/grupo-maquina/grupo-maquina.service';

@Component({
  selector: 'jhi-maquina-update',
  templateUrl: './maquina-update.component.html'
})
export class MaquinaUpdateComponent implements OnInit {
  isSaving: boolean;

  grupomaquinas: IGrupoMaquina[];

  editForm = this.fb.group({
    id: [],
    nome: [],
    idGrupoMaquina: [],
    grupoMaquinaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected maquinaService: MaquinaService,
    protected grupoMaquinaService: GrupoMaquinaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ maquina }) => {
      this.updateForm(maquina);
    });
    this.grupoMaquinaService.query({ filter: 'maquina-is-null' }).subscribe(
      (res: HttpResponse<IGrupoMaquina[]>) => {
        if (!this.editForm.get('grupoMaquinaId').value) {
          this.grupomaquinas = res.body;
        } else {
          this.grupoMaquinaService
            .find(this.editForm.get('grupoMaquinaId').value)
            .subscribe(
              (subRes: HttpResponse<IGrupoMaquina>) => (this.grupomaquinas = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(maquina: IMaquina) {
    this.editForm.patchValue({
      id: maquina.id,
      nome: maquina.nome,
      idGrupoMaquina: maquina.idGrupoMaquina,
      grupoMaquinaId: maquina.grupoMaquinaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
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
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value,
      idGrupoMaquina: this.editForm.get(['idGrupoMaquina']).value,
      grupoMaquinaId: this.editForm.get(['grupoMaquinaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaquina>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackGrupoMaquinaById(index: number, item: IGrupoMaquina) {
    return item.id;
  }
}
