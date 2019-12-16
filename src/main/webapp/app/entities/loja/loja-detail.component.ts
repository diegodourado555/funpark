import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILoja } from 'app/shared/model/loja.model';

@Component({
  selector: 'jhi-loja-detail',
  templateUrl: './loja-detail.component.html'
})
export class LojaDetailComponent implements OnInit {
  loja: ILoja;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ loja }) => {
      this.loja = loja;
    });
  }

  previousState() {
    window.history.back();
  }
}
