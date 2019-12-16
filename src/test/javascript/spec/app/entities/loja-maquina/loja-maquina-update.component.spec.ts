import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { LojaMaquinaUpdateComponent } from 'app/entities/loja-maquina/loja-maquina-update.component';
import { LojaMaquinaService } from 'app/entities/loja-maquina/loja-maquina.service';
import { LojaMaquina } from 'app/shared/model/loja-maquina.model';

describe('Component Tests', () => {
  describe('LojaMaquina Management Update Component', () => {
    let comp: LojaMaquinaUpdateComponent;
    let fixture: ComponentFixture<LojaMaquinaUpdateComponent>;
    let service: LojaMaquinaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [LojaMaquinaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LojaMaquinaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LojaMaquinaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LojaMaquinaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LojaMaquina(123);
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
        const entity = new LojaMaquina();
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
