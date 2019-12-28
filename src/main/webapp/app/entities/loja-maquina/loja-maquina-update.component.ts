import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ILojaMaquina, LojaMaquina } from 'app/shared/model/loja-maquina.model';
import { LojaMaquinaService } from './loja-maquina.service';
import { IMaquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from 'app/entities/maquina/maquina.service';
import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from 'app/entities/loja/loja.service';

type SelectableEntity = IMaquina | ILoja;

@Component({
  selector: 'jhi-loja-maquina-update',
  templateUrl: './loja-maquina-update.component.html'
})
export class LojaMaquinaUpdateComponent implements OnInit {
  isSaving = false;

  maquinas: IMaquina[] = [];

  lojas: ILoja[] = [];

  editForm = this.fb.group({
    id: [],
    maquinaId: [],
    lojaId: []
  });

  constructor(
    protected lojaMaquinaService: LojaMaquinaService,
    protected maquinaService: MaquinaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lojaMaquina }) => {
      this.updateForm(lojaMaquina);

      this.maquinaService
        .query({ filter: 'lojamaquina-is-null' })
        .pipe(
          map((res: HttpResponse<IMaquina[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: IMaquina[]) => {
          if (!lojaMaquina.maquinaId) {
            this.maquinas = resBody;
          } else {
            this.maquinaService
              .find(lojaMaquina.maquinaId)
              .pipe(
                map((subRes: HttpResponse<IMaquina>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMaquina[]) => {
                this.maquinas = concatRes;
              });
          }
        });

      this.lojaService
        .query({ filter: 'lojamaquina-is-null' })
        .pipe(
          map((res: HttpResponse<ILoja[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILoja[]) => {
          if (!lojaMaquina.lojaId) {
            this.lojas = resBody;
          } else {
            this.lojaService
              .find(lojaMaquina.lojaId)
              .pipe(
                map((subRes: HttpResponse<ILoja>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILoja[]) => {
                this.lojas = concatRes;
              });
          }
        });
    });
  }

  updateForm(lojaMaquina: ILojaMaquina): void {
    this.editForm.patchValue({
      id: lojaMaquina.id,
      maquinaId: lojaMaquina.maquinaId,
      lojaId: lojaMaquina.lojaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      maquinaId: this.editForm.get(['maquinaId'])!.value,
      lojaId: this.editForm.get(['lojaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILojaMaquina>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
