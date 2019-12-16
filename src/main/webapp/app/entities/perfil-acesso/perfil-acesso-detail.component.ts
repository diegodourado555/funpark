import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPerfilAcesso } from 'app/shared/model/perfil-acesso.model';

@Component({
  selector: 'jhi-perfil-acesso-detail',
  templateUrl: './perfil-acesso-detail.component.html'
})
export class PerfilAcessoDetailComponent implements OnInit {
  perfilAcesso: IPerfilAcesso;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ perfilAcesso }) => {
      this.perfilAcesso = perfilAcesso;
    });
  }

  previousState() {
    window.history.back();
  }
}
