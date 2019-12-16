import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFunpark } from 'app/shared/model/funpark.model';

@Component({
  selector: 'jhi-funpark-detail',
  templateUrl: './funpark-detail.component.html'
})
export class FunparkDetailComponent implements OnInit {
  funpark: IFunpark;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ funpark }) => {
      this.funpark = funpark;
    });
  }

  previousState() {
    window.history.back();
  }
}
