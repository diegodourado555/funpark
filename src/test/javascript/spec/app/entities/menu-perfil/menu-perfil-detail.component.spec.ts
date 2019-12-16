import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FunparkTestModule } from '../../../test.module';
import { MenuPerfilDetailComponent } from 'app/entities/menu-perfil/menu-perfil-detail.component';
import { MenuPerfil } from 'app/shared/model/menu-perfil.model';

describe('Component Tests', () => {
  describe('MenuPerfil Management Detail Component', () => {
    let comp: MenuPerfilDetailComponent;
    let fixture: ComponentFixture<MenuPerfilDetailComponent>;
    const route = ({ data: of({ menuPerfil: new MenuPerfil(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [FunparkTestModule],
        declarations: [MenuPerfilDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MenuPerfilDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MenuPerfilDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.menuPerfil).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
