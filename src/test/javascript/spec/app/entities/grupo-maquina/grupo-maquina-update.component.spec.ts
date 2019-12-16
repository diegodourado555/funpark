import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { GrupoMaquinaUpdateComponent } from 'app/entities/grupo-maquina/grupo-maquina-update.component';
import { GrupoMaquinaService } from 'app/entities/grupo-maquina/grupo-maquina.service';
import { GrupoMaquina } from 'app/shared/model/grupo-maquina.model';

describe('Component Tests', () => {
  describe('GrupoMaquina Management Update Component', () => {
    let comp: GrupoMaquinaUpdateComponent;
    let fixture: ComponentFixture<GrupoMaquinaUpdateComponent>;
    let service: GrupoMaquinaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [GrupoMaquinaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GrupoMaquinaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GrupoMaquinaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GrupoMaquinaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GrupoMaquina(123);
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
        const entity = new GrupoMaquina();
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
