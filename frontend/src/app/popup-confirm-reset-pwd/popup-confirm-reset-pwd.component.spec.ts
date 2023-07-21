import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupConfirmResetPwdComponent } from './popup-confirm-reset-pwd.component';

describe('PopupConfirmResetPwdComponent', () => {
  let component: PopupConfirmResetPwdComponent;
  let fixture: ComponentFixture<PopupConfirmResetPwdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupConfirmResetPwdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupConfirmResetPwdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
