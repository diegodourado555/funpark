import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { OperadorCaixaUpdateComponent } from 'app/entities/operador-caixa/operador-caixa-update.component';
import { OperadorCaixaService } from 'app/entities/operador-caixa/operador-caixa.service';
import { OperadorCaixa } from 'app/shared/model/operador-caixa.model';

describe('Component Tests', () => {
  describe('OperadorCaixa Management Update Component', () => {
    let comp: OperadorCaixaUpdateComponent;
    let fixture: ComponentFixture<OperadorCaixaUpdateComponent>;
    let service: OperadorCaixaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [OperadorCaixaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OperadorCaixaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OperadorCaixaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OperadorCaixaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new OperadorCaixa(123);
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
        const entity = new OperadorCaixa();
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
