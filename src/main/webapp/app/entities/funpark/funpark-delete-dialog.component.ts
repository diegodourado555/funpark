import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFunpark } from 'app/shared/model/funpark.model';
import { FunparkService } from './funpark.service';

@Component({
  templateUrl: './funpark-delete-dialog.component.html'
})
export class FunparkDeleteDialogComponent {
  funpark: IFunpark;

  constructor(protected funparkService: FunparkService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.funparkService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'funparkListModification',
        content: 'Deleted an funpark'
      });
      this.activeModal.dismiss(true);
    });
  }
}
