import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { DespesasComponent } from './despesas.component';
import { DespesasDetailComponent } from './despesas-detail.component';
import { DespesasUpdateComponent } from './despesas-update.component';
import { DespesasDeleteDialogComponent } from './despesas-delete-dialog.component';
import { despesasRoute } from './despesas.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(despesasRoute)],
  declarations: [DespesasComponent, DespesasDetailComponent, DespesasUpdateComponent, DespesasDeleteDialogComponent],
  entryComponents: [DespesasDeleteDialogComponent]
})
export class FunparkDespesasModule {}
