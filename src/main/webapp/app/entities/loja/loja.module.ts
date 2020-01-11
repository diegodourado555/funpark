import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { TextMaskModule } from 'angular2-text-mask';
import { FormsModule } from '@angular/forms';
import { FunparkSharedModule } from 'app/shared/shared.module';
import { LojaComponent } from './loja.component';
import { LojaDetailComponent } from './loja-detail.component';
import { LojaUpdateComponent } from './loja-update.component';
import { LojaDeleteDialogComponent } from './loja-delete-dialog.component';
import { lojaRoute } from './loja.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(lojaRoute), FormsModule, TextMaskModule],
  declarations: [LojaComponent, LojaDetailComponent, LojaUpdateComponent, LojaDeleteDialogComponent],
  entryComponents: [LojaDeleteDialogComponent]
})
export class FunparkLojaModule {}
