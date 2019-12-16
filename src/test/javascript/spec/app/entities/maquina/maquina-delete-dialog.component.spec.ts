import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { MaquinaDeleteDialogComponent } from 'app/entities/maquina/maquina-delete-dialog.component';
import { MaquinaService } from 'app/entities/maquina/maquina.service';

describe('Component Tests', () => {
  describe('Maquina Management Delete Component', () => {
    let comp: MaquinaDeleteDialogComponent;
    let fixture: ComponentFixture<MaquinaDeleteDialogComponent>;
    let service: MaquinaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [MaquinaDeleteDialogComponent]
      })
        .overrideTemplate(MaquinaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaquinaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaquinaService);
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
