import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { DespesasDeleteDialogComponent } from 'app/entities/despesas/despesas-delete-dialog.component';
import { DespesasService } from 'app/entities/despesas/despesas.service';

describe('Component Tests', () => {
  describe('Despesas Management Delete Component', () => {
    let comp: DespesasDeleteDialogComponent;
    let fixture: ComponentFixture<DespesasDeleteDialogComponent>;
    let service: DespesasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [DespesasDeleteDialogComponent]
      })
        .overrideTemplate(DespesasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DespesasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DespesasService);
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
