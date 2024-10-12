import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../api/category/service/category.service';
import { Categories } from '../../api/category/model/categories';
import { MatListModule } from '@angular/material/list';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';

@Component({
  selector: 'app-category-list-view',
  standalone: true,
  imports: [
    MatListModule,
    MatDividerModule,
    MatButtonModule,
    RouterLink,
    MatIconModule,
    ViewTitleComponent,
  ],
  templateUrl: './category-list-view.component.html',
  styleUrl: './category-list-view.component.css',
})
export class CategoryListViewComponent implements OnInit {
  constructor(private categoryService: CategoryService) {}

  categories: Categories | undefined;

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.categoryService.getAllCategories().subscribe((categories) => {
      this.categories = categories;
    });
  }

  deleteCategory(id: string): void {
    this.categoryService.deleteCategory(id).subscribe(() => {
      this.fetchCategories();
    });
  }
}
