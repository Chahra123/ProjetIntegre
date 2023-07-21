import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopUpCreateUserComponent } from './pop-up-create-user.component';

describe('PopUpCreateUserComponent', () => {
  let component: PopUpCreateUserComponent;
  let fixture: ComponentFixture<PopUpCreateUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopUpCreateUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopUpCreateUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
