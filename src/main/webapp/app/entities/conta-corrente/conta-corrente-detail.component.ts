import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IContaCorrente } from 'app/shared/model/conta-corrente.model';

@Component({
  selector: 'jhi-conta-corrente-detail',
  templateUrl: './conta-corrente-detail.component.html'
})
export class ContaCorrenteDetailComponent implements OnInit {
  contaCorrente: IContaCorrente;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ contaCorrente }) => {
      this.contaCorrente = contaCorrente;
    });
  }

  previousState() {
    window.history.back();
  }
}
