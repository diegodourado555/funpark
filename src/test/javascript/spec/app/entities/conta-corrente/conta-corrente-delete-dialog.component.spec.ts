import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { ContaCorrenteDeleteDialogComponent } from 'app/entities/conta-corrente/conta-corrente-delete-dialog.component';
import { ContaCorrenteService } from 'app/entities/conta-corrente/conta-corrente.service';

describe('Component Tests', () => {
  describe('ContaCorrente Management Delete Component', () => {
    let comp: ContaCorrenteDeleteDialogComponent;
    let fixture: ComponentFixture<ContaCorrenteDeleteDialogComponent>;
    let service: ContaCorrenteService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ContaCorrenteDeleteDialogComponent]
      })
        .overrideTemplate(ContaCorrenteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContaCorrenteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContaCorrenteService);
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
