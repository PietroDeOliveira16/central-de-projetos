import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciarProjetosComponent } from './gerenciar-projetos.component';

describe('GerenciarProjetosComponent', () => {
  let component: GerenciarProjetosComponent;
  let fixture: ComponentFixture<GerenciarProjetosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GerenciarProjetosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GerenciarProjetosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
