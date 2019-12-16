import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { DespesasDetailComponent } from 'app/entities/despesas/despesas-detail.component';
import { Despesas } from 'app/shared/model/despesas.model';

describe('Component Tests', () => {
  describe('Despesas Management Detail Component', () => {
    let comp: DespesasDetailComponent;
    let fixture: ComponentFixture<DespesasDetailComponent>;
    const route = ({ data: of({ despesas: new Despesas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [DespesasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DespesasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DespesasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.despesas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
