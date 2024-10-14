import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryDetailsViewComponent } from './category-details-view.component';

describe('CategoryDetailsViewComponent', () => {
  let component: CategoryDetailsViewComponent;
  let fixture: ComponentFixture<CategoryDetailsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryDetailsViewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CategoryDetailsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
