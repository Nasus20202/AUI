import { Component, OnInit } from '@angular/core';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { CategoryService } from '../../api/category/service/category.service';
import { Category } from '../../api/category/model/category';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';

@Component({
  selector: 'app-add-category-view',
  standalone: true,
  imports: [
    ViewTitleComponent,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ErrorMessageComponent,
  ],
  templateUrl: './edit-category-view.component.html',
  styleUrl: './edit-category-view.component.css',
})
export class EditCategoryViewComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  category: Category | undefined;
  message: string = '';

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.categoryService.getCategoryById(params['id']).subscribe({
        next: (category: Category) => {
          this.category = category;
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
    });
  }

  onSubmit(): void {
    this.message = '';
    if (this.category === undefined) {
      return;
    }
    this.categoryService.updateCategory(this.category).subscribe({
      next: (category: Category) => {
        this.router.navigate(['/categories', category.id]);
      },
      error: (error) => {
        this.message = error.error.message;
      },
    });
  }
}
