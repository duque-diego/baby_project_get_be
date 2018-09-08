import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PromotionRegisterComponent } from './promotion-register.component';

describe('PromotionRegisterComponent', () => {
  let component: PromotionRegisterComponent;
  let fixture: ComponentFixture<PromotionRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PromotionRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PromotionRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
