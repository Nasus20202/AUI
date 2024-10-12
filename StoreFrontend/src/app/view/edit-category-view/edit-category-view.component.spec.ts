import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCategoryViewComponent } from './edit-category-view.component';

describe('EditCategoryViewComponent', () => {
  let component: EditCategoryViewComponent;
  let fixture: ComponentFixture<EditCategoryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditCategoryViewComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EditCategoryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
