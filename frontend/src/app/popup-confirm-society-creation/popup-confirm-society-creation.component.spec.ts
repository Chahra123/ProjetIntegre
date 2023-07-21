import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupConfirmSocietyCreationComponent } from './popup-confirm-society-creation.component';

describe('PopupConfirmSocietyCreationComponent', () => {
  let component: PopupConfirmSocietyCreationComponent;
  let fixture: ComponentFixture<PopupConfirmSocietyCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupConfirmSocietyCreationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupConfirmSocietyCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
