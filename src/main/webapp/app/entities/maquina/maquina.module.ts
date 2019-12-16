import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { MaquinaComponent } from './maquina.component';
import { MaquinaDetailComponent } from './maquina-detail.component';
import { MaquinaUpdateComponent } from './maquina-update.component';
import { MaquinaDeleteDialogComponent } from './maquina-delete-dialog.component';
import { maquinaRoute } from './maquina.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(maquinaRoute)],
  declarations: [MaquinaComponent, MaquinaDetailComponent, MaquinaUpdateComponent, MaquinaDeleteDialogComponent],
  entryComponents: [MaquinaDeleteDialogComponent]
})
export class FunparkMaquinaModule {}
