import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../api/category/service/category.service';
import { ProductService } from '../../api/product/service/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';
import { Product } from '../../api/product/model/product';
import { Category } from '../../api/category/model/category';
import { ProductFormComponent } from '../../component/product-form/product-form.component';

@Component({
  selector: 'app-add-product-view',
  standalone: true,
  imports: [
    ViewTitleComponent,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ErrorMessageComponent,
    ProductFormComponent,
  ],
  templateUrl: './add-product-view.component.html',
  styleUrl: './add-product-view.component.css',
})
export class AddProductViewComponent implements OnInit {
  constructor(
    private categoryService: CategoryService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  message: string = '';
  category: Category | undefined;

  ngOnInit() {
    this.route.params.subscribe((params) => {
      this.categoryService.getCategoryById(params['categoryId']).subscribe({
        next: (category) => {
          this.category = category;
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
    });
  }

  onSubmit(product: Product): void {
    this.message = '';
    if (!this.category) {
      return;
    }
    this.productService.createProduct(product, this.category.id).subscribe({
      next: (product) => {
        this.router.navigate([
          '/categories',
          this.category?.id,
          'products',
          product.id,
        ]);
      },
      error: (error) => {
        this.message = error.error.message;
      },
    });
  }
}
