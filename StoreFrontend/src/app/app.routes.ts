import { Routes } from '@angular/router';
import { HomeViewComponent } from './view/home-view/home-view.component';
import { CategoryListViewComponent } from './view/category-list-view/category-list-view.component';
import { AddCategoryViewComponent } from './view/add-category-view/add-category-view.component';

export const routes: Routes = [
  {
    component: HomeViewComponent,
    path: ''
  },
  {
    component: CategoryListViewComponent,
    path: 'categories'
  },
  {
    component: AddCategoryViewComponent,
    path: 'categories/new'
  }
];
