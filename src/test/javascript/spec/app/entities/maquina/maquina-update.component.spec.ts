import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { MaquinaUpdateComponent } from 'app/entities/maquina/maquina-update.component';
import { MaquinaService } from 'app/entities/maquina/maquina.service';
import { Maquina } from 'app/shared/model/maquina.model';

describe('Component Tests', () => {
  describe('Maquina Management Update Component', () => {
    let comp: MaquinaUpdateComponent;
    let fixture: ComponentFixture<MaquinaUpdateComponent>;
    let service: MaquinaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [MaquinaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MaquinaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaquinaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaquinaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Maquina(123);
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
        const entity = new Maquina();
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
