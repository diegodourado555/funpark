import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { ReceitasComponent } from './receitas.component';
import { ReceitasDetailComponent } from './receitas-detail.component';
import { ReceitasUpdateComponent } from './receitas-update.component';
import { ReceitasDeleteDialogComponent } from './receitas-delete-dialog.component';
import { receitasRoute } from './receitas.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(receitasRoute)],
  declarations: [ReceitasComponent, ReceitasDetailComponent, ReceitasUpdateComponent, ReceitasDeleteDialogComponent],
  entryComponents: [ReceitasDeleteDialogComponent]
})
export class FunparkReceitasModule {}
