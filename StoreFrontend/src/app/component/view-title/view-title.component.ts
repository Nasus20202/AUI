import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-view-title',
  standalone: true,
  imports: [],
  templateUrl: './view-title.component.html',
  styleUrl: './view-title.component.css',
})
export class ViewTitleComponent {
  @Input() title = '';
}
