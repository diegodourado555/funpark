import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPerfilAcesso } from 'app/shared/model/perfil-acesso.model';
import { PerfilAcessoService } from './perfil-acesso.service';

@Component({
  templateUrl: './perfil-acesso-delete-dialog.component.html'
})
export class PerfilAcessoDeleteDialogComponent {
  perfilAcesso: IPerfilAcesso;

  constructor(
    protected perfilAcessoService: PerfilAcessoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.perfilAcessoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'perfilAcessoListModification',
        content: 'Deleted an perfilAcesso'
      });
      this.activeModal.dismiss(true);
    });
  }
}
