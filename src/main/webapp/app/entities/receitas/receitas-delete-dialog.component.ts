import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReceitas } from 'app/shared/model/receitas.model';
import { ReceitasService } from './receitas.service';

@Component({
  templateUrl: './receitas-delete-dialog.component.html'
})
export class ReceitasDeleteDialogComponent {
  receitas: IReceitas;

  constructor(protected receitasService: ReceitasService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.receitasService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'receitasListModification',
        content: 'Deleted an receitas'
      });
      this.activeModal.dismiss(true);
    });
  }
}
