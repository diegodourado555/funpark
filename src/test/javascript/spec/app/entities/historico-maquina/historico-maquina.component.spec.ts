import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoMaquinaComponent } from 'app/entities/historico-maquina/historico-maquina.component';
import { HistoricoMaquinaService } from 'app/entities/historico-maquina/historico-maquina.service';
import { HistoricoMaquina } from 'app/shared/model/historico-maquina.model';

describe('Component Tests', () => {
  describe('HistoricoMaquina Management Component', () => {
    let comp: HistoricoMaquinaComponent;
    let fixture: ComponentFixture<HistoricoMaquinaComponent>;
    let service: HistoricoMaquinaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoMaquinaComponent],
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
        .overrideTemplate(HistoricoMaquinaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistoricoMaquinaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistoricoMaquinaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistoricoMaquina(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historicoMaquinas && comp.historicoMaquinas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new HistoricoMaquina(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.historicoMaquinas && comp.historicoMaquinas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
