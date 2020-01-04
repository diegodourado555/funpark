import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { HistoricoOperadorCaixaService } from 'app/entities/historico-operador-caixa/historico-operador-caixa.service';
import { IHistoricoOperadorCaixa, HistoricoOperadorCaixa } from 'app/shared/model/historico-operador-caixa.model';
import { SituacaoOperadorCaixa } from 'app/shared/model/enumerations/situacao-operador-caixa.model';

describe('Service Tests', () => {
  describe('HistoricoOperadorCaixa Service', () => {
    let injector: TestBed;
    let service: HistoricoOperadorCaixaService;
    let httpMock: HttpTestingController;
    let elemDefault: IHistoricoOperadorCaixa;
    let expectedResult: IHistoricoOperadorCaixa | IHistoricoOperadorCaixa[] | boolean | null;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(HistoricoOperadorCaixaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new HistoricoOperadorCaixa(0, 'AAAAAAA', 0, currentDate, SituacaoOperadorCaixa.ATIVO);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            data: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a HistoricoOperadorCaixa', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            data: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );
        service
          .create(new HistoricoOperadorCaixa())
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a HistoricoOperadorCaixa', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cpf: 1,
            data: currentDate.format(DATE_FORMAT),
            situacao: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp.body));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of HistoricoOperadorCaixa', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cpf: 1,
            data: currentDate.format(DATE_FORMAT),
            situacao: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            data: currentDate
          },
          returnedFromService
        );
        service
          .query()
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a HistoricoOperadorCaixa', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
