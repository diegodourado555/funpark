import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILoja } from 'app/shared/model/loja.model';
import { LojaService } from './loja.service';

@Component({
  templateUrl: './loja-delete-dialog.component.html'
})
export class LojaDeleteDialogComponent {
  loja: ILoja;

  constructor(protected lojaService: LojaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.lojaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'lojaListModification',
        content: 'Deleted an loja'
      });
      this.activeModal.dismiss(true);
    });
  }
}
