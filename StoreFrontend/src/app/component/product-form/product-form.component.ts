import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ErrorMessageComponent } from '../error-message/error-message.component';
import { Product } from '../../api/product/model/product';

@Component({
  selector: 'app-product-form',
  standalone: true,
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ErrorMessageComponent,
  ],
  templateUrl: './product-form.component.html',
  styleUrl: './product-form.component.css',
})
export class ProductFormComponent {
  @Input() product: Product = {
    id: '',
    name: '',
    price: 0,
    stock: 0,
  };
  @Output() submit = new EventEmitter<Product>();
  @Input() message: string = '';

  onSubmit(): void {
    if (this.product) {
      this.submit.emit(this.product);
    }
  }
}
