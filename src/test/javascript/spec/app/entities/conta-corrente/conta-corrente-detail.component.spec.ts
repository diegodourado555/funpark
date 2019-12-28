import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { ContaCorrenteDetailComponent } from 'app/entities/conta-corrente/conta-corrente-detail.component';
import { ContaCorrente } from 'app/shared/model/conta-corrente.model';

describe('Component Tests', () => {
  describe('ContaCorrente Management Detail Component', () => {
    let comp: ContaCorrenteDetailComponent;
    let fixture: ComponentFixture<ContaCorrenteDetailComponent>;
    const route = ({ data: of({ contaCorrente: new ContaCorrente(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ContaCorrenteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ContaCorrenteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ContaCorrenteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load contaCorrente on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.contaCorrente).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
