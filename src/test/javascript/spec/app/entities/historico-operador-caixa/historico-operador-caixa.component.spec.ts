import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoOperadorCaixaComponent } from 'app/entities/historico-operador-caixa/historico-operador-caixa.component';
import { HistoricoOperadorCaixaService } from 'app/entities/historico-operador-caixa/historico-operador-caixa.service';
import { HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';

describe('Component Tests', () => {
  describe('HistoricoOperadorCaixa Management Component', () => {
    let comp: HistoricoOperadorCaixaComponent;
    let fixture: ComponentFixture<HistoricoOperadorCaixaComponent>;
    let service: HistoricoOperadorCaixaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoOperadorCaixaComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(HistoricoOperadorCaixaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistoricoOperadorCaixaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoOperadorCaixaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistoricoOperadorCaixa(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historicoOperadorCaixas && comp.historicoOperadorCaixas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistoricoOperadorCaixa(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historicoOperadorCaixas && comp.historicoOperadorCaixas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
