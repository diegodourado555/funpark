import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { LojaMaquinaDeleteDialogComponent } from 'app/entities/loja-maquina/loja-maquina-delete-dialog.component';
import { LojaMaquinaService } from 'app/entities/loja-maquina/loja-maquina.service';

describe('Component Tests', () => {
  describe('LojaMaquina Management Delete Component', () => {
    let comp: LojaMaquinaDeleteDialogComponent;
    let fixture: ComponentFixture<LojaMaquinaDeleteDialogComponent>;
    let service: LojaMaquinaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [LojaMaquinaDeleteDialogComponent]
      })
        .overrideTemplate(LojaMaquinaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LojaMaquinaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LojaMaquinaService);
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
