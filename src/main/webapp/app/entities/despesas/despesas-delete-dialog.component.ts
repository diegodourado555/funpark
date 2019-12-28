import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDespesas } from 'app/shared/model/despesas.model';
import { DespesasService } from './despesas.service';

@Component({
  templateUrl: './despesas-delete-dialog.component.html'
})
export class DespesasDeleteDialogComponent {
  despesas?: IDespesas;

  constructor(protected despesasService: DespesasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.despesasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('despesasListModification');
      this.activeModal.close();
    });
  }
}
