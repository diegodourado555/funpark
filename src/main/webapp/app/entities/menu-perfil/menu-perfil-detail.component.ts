import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMenuPerfil } from 'app/shared/model/menu-perfil.model';

@Component({
  selector: 'jhi-menu-perfil-detail',
  templateUrl: './menu-perfil-detail.component.html'
})
export class MenuPerfilDetailComponent implements OnInit {
  menuPerfil: IMenuPerfil;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ menuPerfil }) => {
      this.menuPerfil = menuPerfil;
    });
  }

  previousState() {
    window.history.back();
  }
}
