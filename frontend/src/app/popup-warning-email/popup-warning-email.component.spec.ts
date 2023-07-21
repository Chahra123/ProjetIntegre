import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupWarningEmailComponent } from './popup-warning-email.component';

describe('PopupWarningEmailComponent', () => {
  let component: PopupWarningEmailComponent;
  let fixture: ComponentFixture<PopupWarningEmailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupWarningEmailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupWarningEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
