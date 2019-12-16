import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { ReceitasDeleteDialogComponent } from 'app/entities/receitas/receitas-delete-dialog.component';
import { ReceitasService } from 'app/entities/receitas/receitas.service';

describe('Component Tests', () => {
  describe('Receitas Management Delete Component', () => {
    let comp: ReceitasDeleteDialogComponent;
    let fixture: ComponentFixture<ReceitasDeleteDialogComponent>;
    let service: ReceitasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ReceitasDeleteDialogComponent]
      })
        .overrideTemplate(ReceitasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReceitasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReceitasService);
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
