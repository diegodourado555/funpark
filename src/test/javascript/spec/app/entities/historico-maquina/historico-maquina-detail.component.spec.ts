import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { HistoricoMaquinaDetailComponent } from 'app/entities/historico-maquina/historico-maquina-detail.component';
import { HistoricoMaquina } from 'app/shared/model/historico-maquina.model';

describe('Component Tests', () => {
  describe('HistoricoMaquina Management Detail Component', () => {
    let comp: HistoricoMaquinaDetailComponent;
    let fixture: ComponentFixture<HistoricoMaquinaDetailComponent>;
    const route = ({ data: of({ historicoMaquina: new HistoricoMaquina(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [HistoricoMaquinaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HistoricoMaquinaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistoricoMaquinaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load historicoMaquina on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historicoMaquina).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
