import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../api/product/service/product.service';
import { Category } from '../../api/category/model/category';
import { Product } from '../../api/product/model/product';
import { CategoryService } from '../../api/category/service/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';

@Component({
  selector: 'app-edit-product-view',
  standalone: true,
  imports: [
    ViewTitleComponent,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ErrorMessageComponent,
  ],
  templateUrl: './edit-product-view.component.html',
  styleUrl: './edit-product-view.component.css',
})
export class EditProductViewComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  product: Product | undefined;
  category: Category | undefined;
  message: string = '';

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.productService.getProductById(params['productId']).subscribe({
        next: (product) => {
          this.product = product;
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
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

  onSubmit(): void {
    this.message = '';
    if (this.product) {
      this.productService.updateProduct(this.product).subscribe({
        next: (product) => {
          this.router.navigate(['/categories', this.category?.id]);
        },
        error: (error) => {
          this.message = error.error.message;
        },
      });
    }
  }
}
