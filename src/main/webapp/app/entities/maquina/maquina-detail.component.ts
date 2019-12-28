import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMaquina } from 'app/shared/model/maquina.model';

@Component({
  selector: 'jhi-maquina-detail',
  templateUrl: './maquina-detail.component.html'
})
export class MaquinaDetailComponent implements OnInit {
  maquina: IMaquina | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maquina }) => {
      this.maquina = maquina;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
