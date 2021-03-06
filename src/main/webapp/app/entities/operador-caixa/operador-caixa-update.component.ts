import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOperadorCaixa, OperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from './operador-caixa.service';
import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from 'app/entities/loja/loja.service';

@Component({
  selector: 'jhi-operador-caixa-update',
  templateUrl: './operador-caixa-update.component.html'
})
export class OperadorCaixaUpdateComponent implements OnInit {
  public myModel = '';
  public mask = [/[0-9]/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  isSaving = false;

  lojas: ILoja[] = [];

  editForm = this.fb.group({
    id: [],
    nome: [],
    cpf: [],
    situacao: [],
    lojaId: []
  });

  constructor(
    protected operadorCaixaService: OperadorCaixaService,
    protected lojaService: LojaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ operadorCaixa }) => {
      this.updateForm(operadorCaixa);
      this.myModel = operadorCaixa.cpf;

      this.lojaService
        .query()
        .pipe(
          map((res: HttpResponse<ILoja[]>) => {
            return res.body ? res.body : [];
          })
        )
        .subscribe((resBody: ILoja[]) => (this.lojas = resBody));
    });
  }

  updateForm(operadorCaixa: IOperadorCaixa): void {
    this.editForm.patchValue({
      id: operadorCaixa.id,
      nome: operadorCaixa.nome,
      cpf: operadorCaixa.cpf,
      situacao: operadorCaixa.situacao,
      lojaId: operadorCaixa.lojaId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      situacao: this.editForm.get(['situacao'])!.value,
      lojaId: this.editForm.get(['lojaId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOperadorCaixa>>): void {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILoja): any {
    return item.id;
  }
}
