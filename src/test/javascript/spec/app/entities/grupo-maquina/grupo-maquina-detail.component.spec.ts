import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { GrupoMaquinaDetailComponent } from 'app/entities/grupo-maquina/grupo-maquina-detail.component';
import { GrupoMaquina } from 'app/shared/model/grupo-maquina.model';

describe('Component Tests', () => {
  describe('GrupoMaquina Management Detail Component', () => {
    let comp: GrupoMaquinaDetailComponent;
    let fixture: ComponentFixture<GrupoMaquinaDetailComponent>;
    const route = ({ data: of({ grupoMaquina: new GrupoMaquina(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [GrupoMaquinaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GrupoMaquinaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GrupoMaquinaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load grupoMaquina on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.grupoMaquina).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
