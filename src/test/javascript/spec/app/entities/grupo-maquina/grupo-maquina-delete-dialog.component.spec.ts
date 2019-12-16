import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { GrupoMaquinaDeleteDialogComponent } from 'app/entities/grupo-maquina/grupo-maquina-delete-dialog.component';
import { GrupoMaquinaService } from 'app/entities/grupo-maquina/grupo-maquina.service';

describe('Component Tests', () => {
  describe('GrupoMaquina Management Delete Component', () => {
    let comp: GrupoMaquinaDeleteDialogComponent;
    let fixture: ComponentFixture<GrupoMaquinaDeleteDialogComponent>;
    let service: GrupoMaquinaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [GrupoMaquinaDeleteDialogComponent]
      })
        .overrideTemplate(GrupoMaquinaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GrupoMaquinaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GrupoMaquinaService);
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
