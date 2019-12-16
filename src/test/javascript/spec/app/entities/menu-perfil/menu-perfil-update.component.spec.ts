import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { MenuPerfilUpdateComponent } from 'app/entities/menu-perfil/menu-perfil-update.component';
import { MenuPerfilService } from 'app/entities/menu-perfil/menu-perfil.service';
import { MenuPerfil } from 'app/shared/model/menu-perfil.model';

describe('Component Tests', () => {
  describe('MenuPerfil Management Update Component', () => {
    let comp: MenuPerfilUpdateComponent;
    let fixture: ComponentFixture<MenuPerfilUpdateComponent>;
    let service: MenuPerfilService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [MenuPerfilUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MenuPerfilUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MenuPerfilUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MenuPerfilService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MenuPerfil(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MenuPerfil();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
