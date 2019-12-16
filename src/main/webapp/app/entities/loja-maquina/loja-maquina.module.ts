import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { LojaMaquinaComponent } from './loja-maquina.component';
import { LojaMaquinaDetailComponent } from './loja-maquina-detail.component';
import { LojaMaquinaUpdateComponent } from './loja-maquina-update.component';
import { LojaMaquinaDeleteDialogComponent } from './loja-maquina-delete-dialog.component';
import { lojaMaquinaRoute } from './loja-maquina.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(lojaMaquinaRoute)],
  declarations: [LojaMaquinaComponent, LojaMaquinaDetailComponent, LojaMaquinaUpdateComponent, LojaMaquinaDeleteDialogComponent],
  entryComponents: [LojaMaquinaDeleteDialogComponent]
})
export class FunparkLojaMaquinaModule {}
