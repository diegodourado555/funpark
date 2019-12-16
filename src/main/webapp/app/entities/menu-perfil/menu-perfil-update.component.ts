import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IMenuPerfil, MenuPerfil } from 'app/shared/model/menu-perfil.model';
import { MenuPerfilService } from './menu-perfil.service';
import { IMenu } from 'app/shared/model/menu.model';
import { MenuService } from 'app/entities/menu/menu.service';
import { IPerfilAcesso } from 'app/shared/model/perfil-acesso.model';
import { PerfilAcessoService } from 'app/entities/perfil-acesso/perfil-acesso.service';

@Component({
  selector: 'jhi-menu-perfil-update',
  templateUrl: './menu-perfil-update.component.html'
})
export class MenuPerfilUpdateComponent implements OnInit {
  isSaving: boolean;

  menus: IMenu[];

  perfils: IPerfilAcesso[];

  editForm = this.fb.group({
    id: [],
    idMenu: [],
    idPerfil: [],
    menuId: [],
    perfilId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected menuPerfilService: MenuPerfilService,
    protected menuService: MenuService,
    protected perfilAcessoService: PerfilAcessoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ menuPerfil }) => {
      this.updateForm(menuPerfil);
    });
    this.menuService.query({ filter: 'menuperfil-is-null' }).subscribe(
      (res: HttpResponse<IMenu[]>) => {
        if (!this.editForm.get('menuId').value) {
          this.menus = res.body;
        } else {
          this.menuService
            .find(this.editForm.get('menuId').value)
            .subscribe(
              (subRes: HttpResponse<IMenu>) => (this.menus = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.perfilAcessoService.query({ filter: 'menuperfil-is-null' }).subscribe(
      (res: HttpResponse<IPerfilAcesso[]>) => {
        if (!this.editForm.get('perfilId').value) {
          this.perfils = res.body;
        } else {
          this.perfilAcessoService
            .find(this.editForm.get('perfilId').value)
            .subscribe(
              (subRes: HttpResponse<IPerfilAcesso>) => (this.perfils = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(menuPerfil: IMenuPerfil) {
    this.editForm.patchValue({
      id: menuPerfil.id,
      idMenu: menuPerfil.idMenu,
      idPerfil: menuPerfil.idPerfil,
      menuId: menuPerfil.menuId,
      perfilId: menuPerfil.perfilId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const menuPerfil = this.createFromForm();
    if (menuPerfil.id !== undefined) {
      this.subscribeToSaveResponse(this.menuPerfilService.update(menuPerfil));
    } else {
      this.subscribeToSaveResponse(this.menuPerfilService.create(menuPerfil));
    }
  }

  private createFromForm(): IMenuPerfil {
    return {
      ...new MenuPerfil(),
      id: this.editForm.get(['id']).value,
      idMenu: this.editForm.get(['idMenu']).value,
      idPerfil: this.editForm.get(['idPerfil']).value,
      menuId: this.editForm.get(['menuId']).value,
      perfilId: this.editForm.get(['perfilId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMenuPerfil>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMenuById(index: number, item: IMenu) {
    return item.id;
  }

  trackPerfilAcessoById(index: number, item: IPerfilAcesso) {
    return item.id;
  }
}
