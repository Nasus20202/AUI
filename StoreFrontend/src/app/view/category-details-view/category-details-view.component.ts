import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../api/category/service/category.service';
import { Category } from '../../api/category/model/category';
import { ActivatedRoute } from '@angular/router';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { Products } from '../../api/product/model/products';
import { ProductService } from '../../api/product/service/product.service';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';

@Component({
  selector: 'app-category-details-view',
  standalone: true,
  imports: [
    ViewTitleComponent,
    MatCardModule,
    MatButtonModule,
    RouterLink,
    MatListModule,
    MatIconModule,
    ErrorMessageComponent,
  ],
  templateUrl: './category-details-view.component.html',
  styleUrl: './category-details-view.component.css',
})
export class CategoryDetailsViewComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  message: string = '';
  category: Category | undefined;
  products: Products | undefined;

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.categoryService.getCategoryById(params['id']).subscribe({
        next: (category: Category) => {
          this.category = category;
          this.fetchProducts();
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
    });
  }

  fetchProducts(): void {
    if (this.category) {
      this.productService.getProductsByCategoryId(this.category.id).subscribe({
        next: (products: Products) => {
          this.products = products;
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
    }
  }

  deleteProduct(id: string): void {
    this.productService.deleteProduct(id).subscribe(() => {
      this.fetchProducts();
    });
  }
}
