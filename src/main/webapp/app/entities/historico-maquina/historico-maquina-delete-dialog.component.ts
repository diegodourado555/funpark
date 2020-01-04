import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistoricoMaquina } from 'app/shared/model/historico-maquina.model';
import { HistoricoMaquinaService } from './historico-maquina.service';

@Component({
  templateUrl: './historico-maquina-delete-dialog.component.html'
})
export class HistoricoMaquinaDeleteDialogComponent {
  historicoMaquina?: IHistoricoMaquina;

  constructor(
    protected historicoMaquinaService: HistoricoMaquinaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.historicoMaquinaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('historicoMaquinaListModification');
      this.activeModal.close();
    });
  }
}
