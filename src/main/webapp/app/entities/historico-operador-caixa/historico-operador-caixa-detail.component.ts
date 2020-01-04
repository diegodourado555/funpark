import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

@Component({
  selector: 'jhi-historico-operador-caixa-detail',
  templateUrl: './historico-operador-caixa-detail.component.html'
})
export class HistoricoOperadorCaixaDetailComponent implements OnInit {
  historicoOperadorCaixa: IHistoricoOperadorCaixa | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoOperadorCaixa }) => {
      this.historicoOperadorCaixa = historicoOperadorCaixa;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
