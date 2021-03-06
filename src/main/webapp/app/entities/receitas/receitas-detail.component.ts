import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReceitas } from 'app/shared/model/receitas.model';

@Component({
  selector: 'jhi-receitas-detail',
  templateUrl: './receitas-detail.component.html'
})
export class ReceitasDetailComponent implements OnInit {
  receitas: IReceitas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ receitas }) => {
      this.receitas = receitas;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
