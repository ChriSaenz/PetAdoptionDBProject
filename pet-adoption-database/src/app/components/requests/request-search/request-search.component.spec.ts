import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestSearchComponent } from './request-search.component';

describe('RequestSearchComponent', () => {
  let component: RequestSearchComponent;
  let fixture: ComponentFixture<RequestSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestSearchComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
