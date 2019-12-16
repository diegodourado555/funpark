import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { MaquinaDetailComponent } from 'app/entities/maquina/maquina-detail.component';
import { Maquina } from 'app/shared/model/maquina.model';

describe('Component Tests', () => {
  describe('Maquina Management Detail Component', () => {
    let comp: MaquinaDetailComponent;
    let fixture: ComponentFixture<MaquinaDetailComponent>;
    const route = ({ data: of({ maquina: new Maquina(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [MaquinaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MaquinaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MaquinaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.maquina).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
