import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  // Variable que guarda el formulario actual que se debe mostrar
  currentForm: string = '';

  // Método para cambiar el formulario
  showForm(form: string): void {
    this.currentForm = form;
  }
}
