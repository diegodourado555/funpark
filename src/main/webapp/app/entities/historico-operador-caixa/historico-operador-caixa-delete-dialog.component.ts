import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';
import { HistoricoOperadorCaixaService } from './historico-operador-caixa.service';

@Component({
  templateUrl: './historico-operador-caixa-delete-dialog.component.html'
})
export class HistoricoOperadorCaixaDeleteDialogComponent {
  historicoOperadorCaixa?: IHistoricoOperadorCaixa;

  constructor(
    protected historicoOperadorCaixaService: HistoricoOperadorCaixaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.historicoOperadorCaixaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('historicoOperadorCaixaListModification');
      this.activeModal.close();
    });
  }
}
