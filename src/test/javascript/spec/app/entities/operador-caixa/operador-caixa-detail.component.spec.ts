import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { OperadorCaixaDetailComponent } from 'app/entities/operador-caixa/operador-caixa-detail.component';
import { OperadorCaixa } from 'app/shared/model/operador-caixa.model';

describe('Component Tests', () => {
  describe('OperadorCaixa Management Detail Component', () => {
    let comp: OperadorCaixaDetailComponent;
    let fixture: ComponentFixture<OperadorCaixaDetailComponent>;
    const route = ({ data: of({ operadorCaixa: new OperadorCaixa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [OperadorCaixaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OperadorCaixaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OperadorCaixaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.operadorCaixa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
