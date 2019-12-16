import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILojaMaquina } from 'app/shared/model/loja-maquina.model';

@Component({
  selector: 'jhi-loja-maquina-detail',
  templateUrl: './loja-maquina-detail.component.html'
})
export class LojaMaquinaDetailComponent implements OnInit {
  lojaMaquina: ILojaMaquina;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ lojaMaquina }) => {
      this.lojaMaquina = lojaMaquina;
    });
  }

  previousState() {
    window.history.back();
  }
}
