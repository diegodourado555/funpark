import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoMaquinaUpdateComponent } from 'app/entities/historico-maquina/historico-maquina-update.component';
import { HistoricoMaquinaService } from 'app/entities/historico-maquina/historico-maquina.service';
import { HistoricoMaquina } from 'app/shared/model/historico-maquina.model';

describe('Component Tests', () => {
  describe('HistoricoMaquina Management Update Component', () => {
    let comp: HistoricoMaquinaUpdateComponent;
    let fixture: ComponentFixture<HistoricoMaquinaUpdateComponent>;
    let service: HistoricoMaquinaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoMaquinaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HistoricoMaquinaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistoricoMaquinaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoMaquinaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistoricoMaquina(123);
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
        const entity = new HistoricoMaquina();
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
