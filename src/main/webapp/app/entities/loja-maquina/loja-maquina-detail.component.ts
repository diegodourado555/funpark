import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILojaMaquina } from 'app/shared/model/loja-maquina.model';

@Component({
  selector: 'jhi-loja-maquina-detail',
  templateUrl: './loja-maquina-detail.component.html'
})
export class LojaMaquinaDetailComponent implements OnInit {
  lojaMaquina: ILojaMaquina | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lojaMaquina }) => {
      this.lojaMaquina = lojaMaquina;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
