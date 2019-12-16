import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsuario } from 'app/shared/model/usuario.model';
import { UsuarioService } from './usuario.service';

@Component({
  templateUrl: './usuario-delete-dialog.component.html'
})
export class UsuarioDeleteDialogComponent {
  usuario: IUsuario;

  constructor(protected usuarioService: UsuarioService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.usuarioService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'usuarioListModification',
        content: 'Deleted an usuario'
      });
      this.activeModal.dismiss(true);
    });
  }
}
