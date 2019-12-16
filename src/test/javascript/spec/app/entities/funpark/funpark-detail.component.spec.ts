import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { FunparkDetailComponent } from 'app/entities/funpark/funpark-detail.component';
import { Funpark } from 'app/shared/model/funpark.model';

describe('Component Tests', () => {
  describe('Funpark Management Detail Component', () => {
    let comp: FunparkDetailComponent;
    let fixture: ComponentFixture<FunparkDetailComponent>;
    const route = ({ data: of({ funpark: new Funpark(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [FunparkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FunparkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FunparkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.funpark).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
