import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { FunparkSharedModule } from 'app/shared/shared.module';
import { ContaCorrenteComponent } from './conta-corrente.component';
import { ContaCorrenteDetailComponent } from './conta-corrente-detail.component';
import { ContaCorrenteUpdateComponent } from './conta-corrente-update.component';
import { ContaCorrenteDeleteDialogComponent } from './conta-corrente-delete-dialog.component';
import { contaCorrenteRoute } from './conta-corrente.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(contaCorrenteRoute), CurrencyMaskModule],
  declarations: [ContaCorrenteComponent, ContaCorrenteDetailComponent, ContaCorrenteUpdateComponent, ContaCorrenteDeleteDialogComponent],
  entryComponents: [ContaCorrenteDeleteDialogComponent]
})
export class FunparkContaCorrenteModule {}
