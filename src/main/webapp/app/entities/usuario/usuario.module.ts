import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { UsuarioComponent } from './usuario.component';
import { UsuarioDetailComponent } from './usuario-detail.component';
import { UsuarioUpdateComponent } from './usuario-update.component';
import { UsuarioDeleteDialogComponent } from './usuario-delete-dialog.component';
import { usuarioRoute } from './usuario.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(usuarioRoute)],
  declarations: [UsuarioComponent, UsuarioDetailComponent, UsuarioUpdateComponent, UsuarioDeleteDialogComponent],
  entryComponents: [UsuarioDeleteDialogComponent]
})
export class FunparkUsuarioModule {}
