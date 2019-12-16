import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { OperadorCaixaDeleteDialogComponent } from 'app/entities/operador-caixa/operador-caixa-delete-dialog.component';
import { OperadorCaixaService } from 'app/entities/operador-caixa/operador-caixa.service';

describe('Component Tests', () => {
  describe('OperadorCaixa Management Delete Component', () => {
    let comp: OperadorCaixaDeleteDialogComponent;
    let fixture: ComponentFixture<OperadorCaixaDeleteDialogComponent>;
    let service: OperadorCaixaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [OperadorCaixaDeleteDialogComponent]
      })
        .overrideTemplate(OperadorCaixaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperadorCaixaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperadorCaixaService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
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
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
