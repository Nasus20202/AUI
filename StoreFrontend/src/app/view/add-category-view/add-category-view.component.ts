import { Component } from '@angular/core';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { CategoryService } from '../../api/category/service/category.service';
import { Category } from '../../api/category/model/category';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';
import { Router } from '@angular/router';
import { CategoryFormComponent } from '../../component/category-form/category-form.component';

@Component({
  selector: 'app-add-category-view',
  standalone: true,
  imports: [ViewTitleComponent, ErrorMessageComponent, CategoryFormComponent],
  templateUrl: './add-category-view.component.html',
  styleUrl: './add-category-view.component.css',
})
export class AddCategoryViewComponent {
  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  message: string = '';

  onSubmit(category: Category): void {
    this.message = '';
    this.categoryService.createCategory(category).subscribe({
      next: (category: Category) => {
        this.router.navigate(['/categories', category.id]);
      },
      error: (error) => {
        this.message = error.error.message;
      },
    });
  }
}
