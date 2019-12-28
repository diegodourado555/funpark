import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGrupoMaquina } from 'app/shared/model/grupo-maquina.model';
import { GrupoMaquinaService } from './grupo-maquina.service';

@Component({
  templateUrl: './grupo-maquina-delete-dialog.component.html'
})
export class GrupoMaquinaDeleteDialogComponent {
  grupoMaquina?: IGrupoMaquina;

  constructor(
    protected grupoMaquinaService: GrupoMaquinaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.grupoMaquinaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('grupoMaquinaListModification');
      this.activeModal.close();
    });
  }
}
