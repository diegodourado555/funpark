import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistoricoMaquina } from 'app/shared/model/historico-maquina.model';

@Component({
  selector: 'jhi-historico-maquina-detail',
  templateUrl: './historico-maquina-detail.component.html'
})
export class HistoricoMaquinaDetailComponent implements OnInit {
  historicoMaquina: IHistoricoMaquina | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ historicoMaquina }) => {
      this.historicoMaquina = historicoMaquina;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
