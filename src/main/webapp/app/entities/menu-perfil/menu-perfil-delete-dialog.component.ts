import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMenuPerfil } from 'app/shared/model/menu-perfil.model';
import { MenuPerfilService } from './menu-perfil.service';

@Component({
  templateUrl: './menu-perfil-delete-dialog.component.html'
})
export class MenuPerfilDeleteDialogComponent {
  menuPerfil: IMenuPerfil;

  constructor(
    protected menuPerfilService: MenuPerfilService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.menuPerfilService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'menuPerfilListModification',
        content: 'Deleted an menuPerfil'
      });
      this.activeModal.dismiss(true);
    });
  }
}
