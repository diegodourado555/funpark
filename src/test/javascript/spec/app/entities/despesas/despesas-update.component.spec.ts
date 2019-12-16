import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { DespesasUpdateComponent } from 'app/entities/despesas/despesas-update.component';
import { DespesasService } from 'app/entities/despesas/despesas.service';
import { Despesas } from 'app/shared/model/despesas.model';

describe('Component Tests', () => {
  describe('Despesas Management Update Component', () => {
    let comp: DespesasUpdateComponent;
    let fixture: ComponentFixture<DespesasUpdateComponent>;
    let service: DespesasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [DespesasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DespesasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DespesasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DespesasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Despesas(123);
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
        const entity = new Despesas();
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
