import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common'; // Importa CommonModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule], // Añade CommonModule aquí
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
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
