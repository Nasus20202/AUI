import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../api/product/service/product.service';
import { Product } from '../../api/product/model/product';
import { ActivatedRoute } from '@angular/router';
import { Category } from '../../api/category/model/category';
import { CategoryService } from '../../api/category/service/category.service';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { ErrorMessageComponent } from '../../component/error-message/error-message.component';

@Component({
  selector: 'app-product-details-view',
  standalone: true,
  imports: [
    ViewTitleComponent,
    MatCardModule,
    RouterLink,
    MatButtonModule,
    ErrorMessageComponent,
  ],
  templateUrl: './product-details-view.component.html',
  styleUrl: './product-details-view.component.css',
})
export class ProductDetailsViewComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {}

  message: string = '';
  category: Category | undefined;
  product: Product | undefined;

  ngOnInit() {
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
}
