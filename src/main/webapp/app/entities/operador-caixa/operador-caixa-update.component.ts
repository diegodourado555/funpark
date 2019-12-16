import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IOperadorCaixa, OperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from './operador-caixa.service';
import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from 'app/entities/loja/loja.service';

@Component({
  selector: 'jhi-operador-caixa-update',
  templateUrl: './operador-caixa-update.component.html'
})
export class OperadorCaixaUpdateComponent implements OnInit {
  isSaving: boolean;

  lojas: ILoja[];

  editForm = this.fb.group({
    id: [],
    nome: [],
    cpf: [],
    idLoja: [],
    lojaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected operadorCaixaService: OperadorCaixaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ operadorCaixa }) => {
      this.updateForm(operadorCaixa);
    });
    this.lojaService.query({ filter: 'operadorcaixa-is-null' }).subscribe(
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

  updateForm(operadorCaixa: IOperadorCaixa) {
    this.editForm.patchValue({
      id: operadorCaixa.id,
      nome: operadorCaixa.nome,
      cpf: operadorCaixa.cpf,
      idLoja: operadorCaixa.idLoja,
      lojaId: operadorCaixa.lojaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const operadorCaixa = this.createFromForm();
    if (operadorCaixa.id !== undefined) {
      this.subscribeToSaveResponse(this.operadorCaixaService.update(operadorCaixa));
    } else {
      this.subscribeToSaveResponse(this.operadorCaixaService.create(operadorCaixa));
    }
  }

  private createFromForm(): IOperadorCaixa {
    return {
      ...new OperadorCaixa(),
      id: this.editForm.get(['id']).value,
      nome: this.editForm.get(['nome']).value,
      cpf: this.editForm.get(['cpf']).value,
      idLoja: this.editForm.get(['idLoja']).value,
      lojaId: this.editForm.get(['lojaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperadorCaixa>>) {
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

  trackLojaById(index: number, item: ILoja) {
    return item.id;
  }
}
