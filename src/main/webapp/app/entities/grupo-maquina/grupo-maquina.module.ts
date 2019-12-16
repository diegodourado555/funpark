import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { GrupoMaquinaComponent } from './grupo-maquina.component';
import { GrupoMaquinaDetailComponent } from './grupo-maquina-detail.component';
import { GrupoMaquinaUpdateComponent } from './grupo-maquina-update.component';
import { GrupoMaquinaDeleteDialogComponent } from './grupo-maquina-delete-dialog.component';
import { grupoMaquinaRoute } from './grupo-maquina.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(grupoMaquinaRoute)],
  declarations: [GrupoMaquinaComponent, GrupoMaquinaDetailComponent, GrupoMaquinaUpdateComponent, GrupoMaquinaDeleteDialogComponent],
  entryComponents: [GrupoMaquinaDeleteDialogComponent]
})
export class FunparkGrupoMaquinaModule {}
