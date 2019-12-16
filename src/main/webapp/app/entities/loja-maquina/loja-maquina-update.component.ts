import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ILojaMaquina, LojaMaquina } from 'app/shared/model/loja-maquina.model';
import { LojaMaquinaService } from './loja-maquina.service';
import { IMaquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from 'app/entities/maquina/maquina.service';
import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from 'app/entities/loja/loja.service';

@Component({
  selector: 'jhi-loja-maquina-update',
  templateUrl: './loja-maquina-update.component.html'
})
export class LojaMaquinaUpdateComponent implements OnInit {
  isSaving: boolean;

  maquinas: IMaquina[];

  lojas: ILoja[];

  editForm = this.fb.group({
    id: [],
    idLoja: [],
    idMaquina: [],
    maquinaId: [],
    lojaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected lojaMaquinaService: LojaMaquinaService,
    protected maquinaService: MaquinaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ lojaMaquina }) => {
      this.updateForm(lojaMaquina);
    });
    this.maquinaService.query({ filter: 'lojamaquina-is-null' }).subscribe(
      (res: HttpResponse<IMaquina[]>) => {
        if (!this.editForm.get('maquinaId').value) {
          this.maquinas = res.body;
        } else {
          this.maquinaService
            .find(this.editForm.get('maquinaId').value)
            .subscribe(
              (subRes: HttpResponse<IMaquina>) => (this.maquinas = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.lojaService.query({ filter: 'lojamaquina-is-null' }).subscribe(
      (res: HttpResponse<ILoja[]>) => {
        if (!this.editForm.get('lojaId').value) {
          this.lojas = res.body;
        } else {
          this.lojaService
            .find(this.editForm.get('lojaId').value)
            .subscribe(
              (subRes: HttpResponse<ILoja>) => (this.lojas = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(lojaMaquina: ILojaMaquina) {
    this.editForm.patchValue({
      id: lojaMaquina.id,
      idLoja: lojaMaquina.idLoja,
      idMaquina: lojaMaquina.idMaquina,
      maquinaId: lojaMaquina.maquinaId,
      lojaId: lojaMaquina.lojaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const lojaMaquina = this.createFromForm();
    if (lojaMaquina.id !== undefined) {
      this.subscribeToSaveResponse(this.lojaMaquinaService.update(lojaMaquina));
    } else {
      this.subscribeToSaveResponse(this.lojaMaquinaService.create(lojaMaquina));
    }
  }

  private createFromForm(): ILojaMaquina {
    return {
      ...new LojaMaquina(),
      id: this.editForm.get(['id']).value,
      idLoja: this.editForm.get(['idLoja']).value,
      idMaquina: this.editForm.get(['idMaquina']).value,
      maquinaId: this.editForm.get(['maquinaId']).value,
      lojaId: this.editForm.get(['lojaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILojaMaquina>>) {
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

  trackMaquinaById(index: number, item: IMaquina) {
    return item.id;
  }

  trackLojaById(index: number, item: ILoja) {
    return item.id;
  }
}
