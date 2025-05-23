import { TestBed } from '@angular/core/testing';

import { ProjetoAbertoService } from './projeto-aberto.service';

describe('ProjetoAbertoService', () => {
  let service: ProjetoAbertoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjetoAbertoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
