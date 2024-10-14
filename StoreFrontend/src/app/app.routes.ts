import { Routes } from '@angular/router';
import { CategoryListViewComponent } from './view/category-list-view/category-list-view.component';
import { AddCategoryViewComponent } from './view/add-category-view/add-category-view.component';
import { EditCategoryViewComponent } from './view/edit-category-view/edit-category-view.component';
import { CategoryDetailsViewComponent } from './view/category-details-view/category-details-view.component';
import { ProductDetailsViewComponent } from './view/product-details-view/product-details-view.component';
import { AddProductViewComponent } from './view/add-product-view/add-product-view.component';
import { EditProductViewComponent } from './view/edit-product-view/edit-product-view.component';

export const routes: Routes = [
  {
    component: CategoryListViewComponent,
    path: 'categories',
  },
  {
    component: AddCategoryViewComponent,
    path: 'categories/new',
  },
  {
    component: CategoryDetailsViewComponent,
    path: 'categories/:id',
  },
  {
    component: EditCategoryViewComponent,
    path: 'categories/:id/edit',
  },
  {
    component: AddProductViewComponent,
    path: 'categories/:categoryId/products/new',
  },
  {
    component: ProductDetailsViewComponent,
    path: 'categories/:categoryId/products/:productId',
  },
  {
    component: EditProductViewComponent,
    path: 'categories/:categoryId/products/:productId/edit',
  },
  {
    component: CategoryListViewComponent,
    path: '**',
  },
];
