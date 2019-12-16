import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FunparkSharedModule } from 'app/shared/shared.module';
import { FunparkComponent } from './funpark.component';
import { FunparkDetailComponent } from './funpark-detail.component';
import { FunparkUpdateComponent } from './funpark-update.component';
import { FunparkDeleteDialogComponent } from './funpark-delete-dialog.component';
import { funparkRoute } from './funpark.route';

@NgModule({
  imports: [FunparkSharedModule, RouterModule.forChild(funparkRoute)],
  declarations: [FunparkComponent, FunparkDetailComponent, FunparkUpdateComponent, FunparkDeleteDialogComponent],
  entryComponents: [FunparkDeleteDialogComponent]
})
export class FunparkFunparkModule {}
