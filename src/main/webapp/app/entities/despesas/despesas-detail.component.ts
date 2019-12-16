import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDespesas } from 'app/shared/model/despesas.model';

@Component({
  selector: 'jhi-despesas-detail',
  templateUrl: './despesas-detail.component.html'
})
export class DespesasDetailComponent implements OnInit {
  despesas: IDespesas;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ despesas }) => {
      this.despesas = despesas;
    });
  }

  previousState() {
    window.history.back();
  }
}
