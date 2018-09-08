import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StoreRegisterComponent } from './store-register.component';

describe('StoreRegisterComponent', () => {
  let component: StoreRegisterComponent;
  let fixture: ComponentFixture<StoreRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StoreRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StoreRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
