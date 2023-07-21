import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupTokenSecurityComponent } from './popup-token-security.component';

describe('PopupTokenSecurityComponent', () => {
  let component: PopupTokenSecurityComponent;
  let fixture: ComponentFixture<PopupTokenSecurityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupTokenSecurityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PopupTokenSecurityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
