import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'funpark',
        loadChildren: () => import('./funpark/funpark.module').then(m => m.FunparkFunparkModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class FunparkEntityModule {}
