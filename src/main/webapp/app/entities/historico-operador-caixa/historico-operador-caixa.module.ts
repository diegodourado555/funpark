import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { HistoricoOperadorCaixaComponent } from './historico-operador-caixa.component';
import { HistoricoOperadorCaixaDetailComponent } from './historico-operador-caixa-detail.component';
import { HistoricoOperadorCaixaUpdateComponent } from './historico-operador-caixa-update.component';
import { HistoricoOperadorCaixaDeleteDialogComponent } from './historico-operador-caixa-delete-dialog.component';
import { historicoOperadorCaixaRoute } from './historico-operador-caixa.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(historicoOperadorCaixaRoute)],
  declarations: [
    HistoricoOperadorCaixaComponent,
    HistoricoOperadorCaixaDetailComponent,
    HistoricoOperadorCaixaUpdateComponent,
    HistoricoOperadorCaixaDeleteDialogComponent
  ],
  entryComponents: [HistoricoOperadorCaixaDeleteDialogComponent]
})
export class FunparkHistoricoOperadorCaixaModule {}
