import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoOperadorCaixaUpdateComponent } from 'app/entities/historico-operador-caixa/historico-operador-caixa-update.component';
import { HistoricoOperadorCaixaService } from 'app/entities/historico-operador-caixa/historico-operador-caixa.service';
import { HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

describe('Component Tests', () => {
  describe('HistoricoOperadorCaixa Management Update Component', () => {
    let comp: HistoricoOperadorCaixaUpdateComponent;
    let fixture: ComponentFixture<HistoricoOperadorCaixaUpdateComponent>;
    let service: HistoricoOperadorCaixaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoOperadorCaixaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HistoricoOperadorCaixaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistoricoOperadorCaixaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoOperadorCaixaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistoricoOperadorCaixa(123);
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
        const entity = new HistoricoOperadorCaixa();
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
