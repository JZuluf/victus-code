import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [CommonModule]  // Asegúrate de importar CommonModule si es necesario
})
export class AppComponent {
  currentForm: string = 'login';

  showForm(form: string): void {
    this.currentForm = form;
  }
}
