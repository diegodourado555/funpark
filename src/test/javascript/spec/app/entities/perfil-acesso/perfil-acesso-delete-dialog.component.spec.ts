import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { PerfilAcessoDeleteDialogComponent } from 'app/entities/perfil-acesso/perfil-acesso-delete-dialog.component';
import { PerfilAcessoService } from 'app/entities/perfil-acesso/perfil-acesso.service';

describe('Component Tests', () => {
  describe('PerfilAcesso Management Delete Component', () => {
    let comp: PerfilAcessoDeleteDialogComponent;
    let fixture: ComponentFixture<PerfilAcessoDeleteDialogComponent>;
    let service: PerfilAcessoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [PerfilAcessoDeleteDialogComponent]
      })
        .overrideTemplate(PerfilAcessoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PerfilAcessoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PerfilAcessoService);
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
