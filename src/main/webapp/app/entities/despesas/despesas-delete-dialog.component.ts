import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDespesas } from 'app/shared/model/despesas.model';
import { DespesasService } from './despesas.service';

@Component({
  templateUrl: './despesas-delete-dialog.component.html'
})
export class DespesasDeleteDialogComponent {
  despesas: IDespesas;

  constructor(protected despesasService: DespesasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.despesasService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'despesasListModification',
        content: 'Deleted an despesas'
      });
      this.activeModal.dismiss(true);
    });
  }
}
