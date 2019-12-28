import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { FunparkTestModule } from '../../../test.module';
import { ContaCorrenteComponent } from 'app/entities/conta-corrente/conta-corrente.component';
import { ContaCorrenteService } from 'app/entities/conta-corrente/conta-corrente.service';
import { ContaCorrente } from 'app/shared/model/conta-corrente.model';

describe('Component Tests', () => {
  describe('ContaCorrente Management Component', () => {
    let comp: ContaCorrenteComponent;
    let fixture: ComponentFixture<ContaCorrenteComponent>;
    let service: ContaCorrenteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ContaCorrenteComponent],
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
        .overrideTemplate(ContaCorrenteComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ContaCorrenteComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ContaCorrenteService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContaCorrente(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contaCorrentes && comp.contaCorrentes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ContaCorrente(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.contaCorrentes && comp.contaCorrentes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
