import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciarUsersComponent } from './gerenciar-users.component';

describe('GerenciarUsersComponent', () => {
  let component: GerenciarUsersComponent;
  let fixture: ComponentFixture<GerenciarUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GerenciarUsersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GerenciarUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
