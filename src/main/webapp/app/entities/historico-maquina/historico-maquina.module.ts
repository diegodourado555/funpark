import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { HistoricoMaquinaComponent } from './historico-maquina.component';
import { HistoricoMaquinaDetailComponent } from './historico-maquina-detail.component';
import { HistoricoMaquinaUpdateComponent } from './historico-maquina-update.component';
import { HistoricoMaquinaDeleteDialogComponent } from './historico-maquina-delete-dialog.component';
import { historicoMaquinaRoute } from './historico-maquina.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(historicoMaquinaRoute)],
  declarations: [
    HistoricoMaquinaComponent,
    HistoricoMaquinaDetailComponent,
    HistoricoMaquinaUpdateComponent,
    HistoricoMaquinaDeleteDialogComponent
  ],
  entryComponents: [HistoricoMaquinaDeleteDialogComponent]
})
export class FunparkHistoricoMaquinaModule {}
