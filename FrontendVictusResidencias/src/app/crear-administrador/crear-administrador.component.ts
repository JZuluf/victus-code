import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-crear-administrador',  // Cambiado para que coincida con el nombre del componente
  standalone: true,
  templateUrl: './crear-administrador.component.html',
  styleUrls: ['./crear-administrador.component.scss'],
  imports: [CommonModule]  // Importa CommonModule si usas directivas como ngIf o ngFor
})
export class CrearAdministradorComponent {
  currentForm: string = 'login';

  showForm(form: string): void {
    this.currentForm = form;
  }
}
