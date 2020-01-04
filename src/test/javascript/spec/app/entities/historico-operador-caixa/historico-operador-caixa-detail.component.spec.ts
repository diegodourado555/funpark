import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoOperadorCaixaDetailComponent } from 'app/entities/historico-operador-caixa/historico-operador-caixa-detail.component';
import { HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

describe('Component Tests', () => {
  describe('HistoricoOperadorCaixa Management Detail Component', () => {
    let comp: HistoricoOperadorCaixaDetailComponent;
    let fixture: ComponentFixture<HistoricoOperadorCaixaDetailComponent>;
    const route = ({ data: of({ historicoOperadorCaixa: new HistoricoOperadorCaixa(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoOperadorCaixaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HistoricoOperadorCaixaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistoricoOperadorCaixaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load historicoOperadorCaixa on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historicoOperadorCaixa).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
