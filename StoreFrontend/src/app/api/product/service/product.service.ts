import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Products } from '../model/products';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private httpClient: HttpClient) {}

  getAllProducts(): Observable<Products> {
    return this.httpClient.get<Products>('/api/products');
  }

  getProductById(id: string): Observable<Product> {
    return this.httpClient.get<Product>(`/api/products/${id}`);
  }

  getProductsByCategoryId(categoryId: string): Observable<Products> {
    return this.httpClient.get<Products>(
      `/api/categories/${categoryId}/products`,
    );
  }

  createProduct(product: Product, categoryId: string): Observable<Product> {
    return this.httpClient.post<Product>(
      `/api/categories/${categoryId}/products`,
      product,
    );
  }

  updateProduct(product: Product): Observable<Product> {
    return this.httpClient.patch<Product>(
      `/api/products/${product.id}`,
      product,
    );
  }

  deleteProduct(id: string): Observable<void> {
    return this.httpClient.delete<void>(`/api/products/${id}`);
  }
}
