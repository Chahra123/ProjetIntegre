import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupResetPasswordComponent } from './popup-reset-password.component';

describe('PopupResetPasswordComponent', () => {
  let component: PopupResetPasswordComponent;
  let fixture: ComponentFixture<PopupResetPasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupResetPasswordComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupResetPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
