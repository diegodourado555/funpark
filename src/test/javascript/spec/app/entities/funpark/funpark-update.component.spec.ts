import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { FunparkUpdateComponent } from 'app/entities/funpark/funpark-update.component';
import { FunparkService } from 'app/entities/funpark/funpark.service';
import { Funpark } from 'app/shared/model/funpark.model';

describe('Component Tests', () => {
  describe('Funpark Management Update Component', () => {
    let comp: FunparkUpdateComponent;
    let fixture: ComponentFixture<FunparkUpdateComponent>;
    let service: FunparkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [FunparkUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FunparkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FunparkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FunparkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Funpark(123);
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
        const entity = new Funpark();
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
