import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categories } from '../model/categories';
import { Category } from '../model/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private httpClient: HttpClient) {}

  getAllCategories(): Observable<Categories> {
    return this.httpClient.get<Categories>('/api/categories');
  }

  getCategoryById(id: string): Observable<Category> {
    return this.httpClient.get<Category>(`/api/categories/${id}`);
  }

  createCategory(category: Category): Observable<Category> {
    return this.httpClient.put<Category>('/api/categories', category);
  }

  updateCategory(category: Category): Observable<Category> {
    return this.httpClient.patch<Category>(
      `/api/categories/${category.id}`,
      category,
    );
  }

  deleteCategory(id: string): Observable<void> {
    return this.httpClient.delete<void>(`/api/categories/${id}`);
  }
}
