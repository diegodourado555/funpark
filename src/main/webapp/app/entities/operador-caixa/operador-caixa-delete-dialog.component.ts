import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOperadorCaixa } from 'app/shared/model/operador-caixa.model';
import { OperadorCaixaService } from './operador-caixa.service';

@Component({
  templateUrl: './operador-caixa-delete-dialog.component.html'
})
export class OperadorCaixaDeleteDialogComponent {
  operadorCaixa: IOperadorCaixa;

  constructor(
    protected operadorCaixaService: OperadorCaixaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.operadorCaixaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'operadorCaixaListModification',
        content: 'Deleted an operadorCaixa'
      });
      this.activeModal.dismiss(true);
    });
  }
}
