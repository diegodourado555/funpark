import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { HistoricoOperadorCaixaDeleteDialogComponent } from 'app/entities/historico-operador-caixa/historico-operador-caixa-delete-dialog.component';
import { HistoricoOperadorCaixaService } from 'app/entities/historico-operador-caixa/historico-operador-caixa.service';

describe('Component Tests', () => {
  describe('HistoricoOperadorCaixa Management Delete Component', () => {
    let comp: HistoricoOperadorCaixaDeleteDialogComponent;
    let fixture: ComponentFixture<HistoricoOperadorCaixaDeleteDialogComponent>;
    let service: HistoricoOperadorCaixaService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoOperadorCaixaDeleteDialogComponent]
      })
        .overrideTemplate(HistoricoOperadorCaixaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistoricoOperadorCaixaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoOperadorCaixaService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.clear();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
