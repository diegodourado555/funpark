import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FunparkTestModule } from '../../../test.module';
import { UsuarioPerfilDeleteDialogComponent } from 'app/entities/usuario-perfil/usuario-perfil-delete-dialog.component';
import { UsuarioPerfilService } from 'app/entities/usuario-perfil/usuario-perfil.service';

describe('Component Tests', () => {
  describe('UsuarioPerfil Management Delete Component', () => {
    let comp: UsuarioPerfilDeleteDialogComponent;
    let fixture: ComponentFixture<UsuarioPerfilDeleteDialogComponent>;
    let service: UsuarioPerfilService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [UsuarioPerfilDeleteDialogComponent]
      })
        .overrideTemplate(UsuarioPerfilDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UsuarioPerfilDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UsuarioPerfilService);
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
