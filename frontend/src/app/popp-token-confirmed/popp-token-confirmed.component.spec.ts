import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoppTokenConfirmedComponent } from './popp-token-confirmed.component';

describe('PoppTokenConfirmedComponent', () => {
  let component: PoppTokenConfirmedComponent;
  let fixture: ComponentFixture<PoppTokenConfirmedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoppTokenConfirmedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PoppTokenConfirmedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
