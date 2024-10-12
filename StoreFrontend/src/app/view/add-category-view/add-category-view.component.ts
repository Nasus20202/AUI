import { Component } from '@angular/core';
import { ViewTitleComponent } from "../../component/view-title/view-title.component";
import { CategoryService } from '../../api/category/service/category.service';
import { Category } from '../../api/category/model/category';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-category-view',
  standalone: true,
  imports: [ViewTitleComponent, MatCardModule, MatButtonModule, MatInputModule, MatFormFieldModule, FormsModule],
  templateUrl: './add-category-view.component.html',
  styleUrl: './add-category-view.component.css'
})
export class AddCategoryViewComponent {

  constructor(private categoryService: CategoryService, private router: Router) { }

  category: Category = {
    id: undefined,
    name: '',
    description: '',
    popularity: 0
  };

  message: string = '';

  onSubmit(): void {
    this.message = '';
    this.categoryService.createCategory(this.category).subscribe(
      {
        next: (category: Category) => {
          this.router.navigate(['/categories']);
        },
        error: (error) => {
          this.message = error.error.message;
      }
    }
    );
  }
}
