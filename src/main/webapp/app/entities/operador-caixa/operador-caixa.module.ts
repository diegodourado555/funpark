import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TextMaskModule } from 'angular2-text-mask';
import { FunparkSharedModule } from 'app/shared/shared.module';
import { OperadorCaixaComponent } from './operador-caixa.component';
import { OperadorCaixaDetailComponent } from './operador-caixa-detail.component';
import { OperadorCaixaUpdateComponent } from './operador-caixa-update.component';
import { OperadorCaixaDeleteDialogComponent } from './operador-caixa-delete-dialog.component';
import { operadorCaixaRoute } from './operador-caixa.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(operadorCaixaRoute), FormsModule, TextMaskModule],
  declarations: [OperadorCaixaComponent, OperadorCaixaDetailComponent, OperadorCaixaUpdateComponent, OperadorCaixaDeleteDialogComponent],
  entryComponents: [OperadorCaixaDeleteDialogComponent]
})
export class FunparkOperadorCaixaModule {}
