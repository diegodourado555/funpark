import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IUsuarioPerfil, UsuarioPerfil } from 'app/shared/model/usuario-perfil.model';
import { UsuarioPerfilService } from './usuario-perfil.service';
import { IUsuario } from 'app/shared/model/usuario.model';
import { UsuarioService } from 'app/entities/usuario/usuario.service';
import { IPerfilAcesso } from 'app/shared/model/perfil-acesso.model';
import { PerfilAcessoService } from 'app/entities/perfil-acesso/perfil-acesso.service';

@Component({
  selector: 'jhi-usuario-perfil-update',
  templateUrl: './usuario-perfil-update.component.html'
})
export class UsuarioPerfilUpdateComponent implements OnInit {
  isSaving: boolean;

  usuarios: IUsuario[];

  perfils: IPerfilAcesso[];

  editForm = this.fb.group({
    id: [],
    idUsuario: [],
    idPerfil: [],
    usuarioId: [],
    perfilId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected usuarioPerfilService: UsuarioPerfilService,
    protected usuarioService: UsuarioService,
    protected perfilAcessoService: PerfilAcessoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ usuarioPerfil }) => {
      this.updateForm(usuarioPerfil);
    });
    this.usuarioService.query({ filter: 'usuarioperfil-is-null' }).subscribe(
      (res: HttpResponse<IUsuario[]>) => {
        if (!this.editForm.get('usuarioId').value) {
          this.usuarios = res.body;
        } else {
          this.usuarioService
            .find(this.editForm.get('usuarioId').value)
            .subscribe(
              (subRes: HttpResponse<IUsuario>) => (this.usuarios = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
    this.perfilAcessoService.query({ filter: 'usuarioperfil-is-null' }).subscribe(
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

  updateForm(usuarioPerfil: IUsuarioPerfil) {
    this.editForm.patchValue({
      id: usuarioPerfil.id,
      idUsuario: usuarioPerfil.idUsuario,
      idPerfil: usuarioPerfil.idPerfil,
      usuarioId: usuarioPerfil.usuarioId,
      perfilId: usuarioPerfil.perfilId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const usuarioPerfil = this.createFromForm();
    if (usuarioPerfil.id !== undefined) {
      this.subscribeToSaveResponse(this.usuarioPerfilService.update(usuarioPerfil));
    } else {
      this.subscribeToSaveResponse(this.usuarioPerfilService.create(usuarioPerfil));
    }
  }

  private createFromForm(): IUsuarioPerfil {
    return {
      ...new UsuarioPerfil(),
      id: this.editForm.get(['id']).value,
      idUsuario: this.editForm.get(['idUsuario']).value,
      idPerfil: this.editForm.get(['idPerfil']).value,
      usuarioId: this.editForm.get(['usuarioId']).value,
      perfilId: this.editForm.get(['perfilId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsuarioPerfil>>) {
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

  trackUsuarioById(index: number, item: IUsuario) {
    return item.id;
  }

  trackPerfilAcessoById(index: number, item: IPerfilAcesso) {
    return item.id;
  }
}
