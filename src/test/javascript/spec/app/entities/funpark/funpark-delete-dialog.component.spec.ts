import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { FunparkDeleteDialogComponent } from 'app/entities/funpark/funpark-delete-dialog.component';
import { FunparkService } from 'app/entities/funpark/funpark.service';

describe('Component Tests', () => {
  describe('Funpark Management Delete Component', () => {
    let comp: FunparkDeleteDialogComponent;
    let fixture: ComponentFixture<FunparkDeleteDialogComponent>;
    let service: FunparkService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [FunparkDeleteDialogComponent]
      })
        .overrideTemplate(FunparkDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FunparkDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FunparkService);
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
