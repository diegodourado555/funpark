import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { ContaCorrenteUpdateComponent } from 'app/entities/conta-corrente/conta-corrente-update.component';
import { ContaCorrenteService } from 'app/entities/conta-corrente/conta-corrente.service';
import { ContaCorrente } from 'app/shared/model/conta-corrente.model';

describe('Component Tests', () => {
  describe('ContaCorrente Management Update Component', () => {
    let comp: ContaCorrenteUpdateComponent;
    let fixture: ComponentFixture<ContaCorrenteUpdateComponent>;
    let service: ContaCorrenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ContaCorrenteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ContaCorrenteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContaCorrenteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContaCorrenteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ContaCorrente(123);
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
        const entity = new ContaCorrente();
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
