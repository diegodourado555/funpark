import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOperadorCaixa } from 'app/shared/model/operador-caixa.model';

@Component({
  selector: 'jhi-operador-caixa-detail',
  templateUrl: './operador-caixa-detail.component.html'
})
export class OperadorCaixaDetailComponent implements OnInit {
  operadorCaixa: IOperadorCaixa;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ operadorCaixa }) => {
      this.operadorCaixa = operadorCaixa;
    });
  }

  previousState() {
    window.history.back();
  }
}
