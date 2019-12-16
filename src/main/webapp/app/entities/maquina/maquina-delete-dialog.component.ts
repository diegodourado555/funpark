import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMaquina } from 'app/shared/model/maquina.model';
import { MaquinaService } from './maquina.service';

@Component({
  templateUrl: './maquina-delete-dialog.component.html'
})
export class MaquinaDeleteDialogComponent {
  maquina: IMaquina;

  constructor(protected maquinaService: MaquinaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.maquinaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'maquinaListModification',
        content: 'Deleted an maquina'
      });
      this.activeModal.dismiss(true);
    });
  }
}
