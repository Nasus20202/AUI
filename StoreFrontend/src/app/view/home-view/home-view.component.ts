import { Component } from '@angular/core';
import { ViewTitleComponent } from '../../component/view-title/view-title.component';

@Component({
  selector: 'app-home-view',
  standalone: true,
  imports: [ViewTitleComponent],
  templateUrl: './home-view.component.html',
  styleUrl: './home-view.component.css'
})
export class HomeViewComponent {

}
