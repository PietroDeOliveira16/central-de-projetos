import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiarioDeBordoComponent } from './diario-de-bordo.component';

describe('DiarioDeBordoComponent', () => {
  let component: DiarioDeBordoComponent;
  let fixture: ComponentFixture<DiarioDeBordoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DiarioDeBordoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiarioDeBordoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
