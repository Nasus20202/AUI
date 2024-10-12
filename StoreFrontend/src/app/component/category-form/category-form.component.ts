import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Category } from '../../api/category/model/category';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ErrorMessageComponent } from '../error-message/error-message.component';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    FormsModule,
    ErrorMessageComponent,
  ],
  templateUrl: './category-form.component.html',
  styleUrl: './category-form.component.css',
})
export class CategoryFormComponent {
  @Input() category: Category = {
    id: '',
    name: '',
    description: '',
    popularity: 0,
  };
  @Output() submit = new EventEmitter<Category>();
  @Input() message: string = '';

  onSubmit(): void {
    if (this.category) {
      this.submit.emit(this.category);
    }
  }
}
