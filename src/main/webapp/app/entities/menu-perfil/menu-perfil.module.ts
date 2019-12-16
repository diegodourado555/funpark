import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { MenuPerfilComponent } from './menu-perfil.component';
import { MenuPerfilDetailComponent } from './menu-perfil-detail.component';
import { MenuPerfilUpdateComponent } from './menu-perfil-update.component';
import { MenuPerfilDeleteDialogComponent } from './menu-perfil-delete-dialog.component';
import { menuPerfilRoute } from './menu-perfil.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(menuPerfilRoute)],
  declarations: [MenuPerfilComponent, MenuPerfilDetailComponent, MenuPerfilUpdateComponent, MenuPerfilDeleteDialogComponent],
  entryComponents: [MenuPerfilDeleteDialogComponent]
})
export class FunparkMenuPerfilModule {}
