import { TestBed } from '@angular/core/testing';

import { CriarProjetoService } from './criar-projeto.service';

describe('CriarProjetoService', () => {
  let service: CriarProjetoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CriarProjetoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
