import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { ReceitasDetailComponent } from 'app/entities/receitas/receitas-detail.component';
import { Receitas } from 'app/shared/model/receitas.model';

describe('Component Tests', () => {
  describe('Receitas Management Detail Component', () => {
    let comp: ReceitasDetailComponent;
    let fixture: ComponentFixture<ReceitasDetailComponent>;
    const route = ({ data: of({ receitas: new Receitas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [ReceitasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ReceitasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ReceitasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.receitas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
