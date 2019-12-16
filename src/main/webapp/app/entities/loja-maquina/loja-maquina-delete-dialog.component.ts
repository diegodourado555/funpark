import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILojaMaquina } from 'app/shared/model/loja-maquina.model';
import { LojaMaquinaService } from './loja-maquina.service';

@Component({
  templateUrl: './loja-maquina-delete-dialog.component.html'
})
export class LojaMaquinaDeleteDialogComponent {
  lojaMaquina: ILojaMaquina;

  constructor(
    protected lojaMaquinaService: LojaMaquinaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.lojaMaquinaService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'lojaMaquinaListModification',
        content: 'Deleted an lojaMaquina'
      });
      this.activeModal.dismiss(true);
    });
  }
}
