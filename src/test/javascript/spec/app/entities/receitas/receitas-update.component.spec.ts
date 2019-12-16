import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { ReceitasUpdateComponent } from 'app/entities/receitas/receitas-update.component';
import { ReceitasService } from 'app/entities/receitas/receitas.service';
import { Receitas } from 'app/shared/model/receitas.model';

describe('Component Tests', () => {
  describe('Receitas Management Update Component', () => {
    let comp: ReceitasUpdateComponent;
    let fixture: ComponentFixture<ReceitasUpdateComponent>;
    let service: ReceitasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ReceitasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ReceitasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ReceitasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ReceitasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Receitas(123);
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
        const entity = new Receitas();
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
