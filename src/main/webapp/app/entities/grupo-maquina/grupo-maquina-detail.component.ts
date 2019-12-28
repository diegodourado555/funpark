import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';

@Component({
  selector: 'jhi-grupo-maquina-detail',
  templateUrl: './grupo-maquina-detail.component.html'
})
export class GrupoMaquinaDetailComponent implements OnInit {
  grupoMaquina: IGrupoMaquina | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ grupoMaquina }) => {
      this.grupoMaquina = grupoMaquina;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
