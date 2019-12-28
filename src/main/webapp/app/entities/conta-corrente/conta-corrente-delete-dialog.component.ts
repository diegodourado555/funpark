import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IContaCorrente } from 'app/shared/model/conta-corrente.model';
import { ContaCorrenteService } from './conta-corrente.service';

@Component({
  templateUrl: './conta-corrente-delete-dialog.component.html'
})
export class ContaCorrenteDeleteDialogComponent {
  contaCorrente?: IContaCorrente;

  constructor(
    protected contaCorrenteService: ContaCorrenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.contaCorrenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('contaCorrenteListModification');
      this.activeModal.close();
    });
  }
}
