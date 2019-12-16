import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { LojaMaquinaDetailComponent } from 'app/entities/loja-maquina/loja-maquina-detail.component';
import { LojaMaquina } from 'app/shared/model/loja-maquina.model';

describe('Component Tests', () => {
  describe('LojaMaquina Management Detail Component', () => {
    let comp: LojaMaquinaDetailComponent;
    let fixture: ComponentFixture<LojaMaquinaDetailComponent>;
    const route = ({ data: of({ lojaMaquina: new LojaMaquina(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [LojaMaquinaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LojaMaquinaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LojaMaquinaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.lojaMaquina).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
