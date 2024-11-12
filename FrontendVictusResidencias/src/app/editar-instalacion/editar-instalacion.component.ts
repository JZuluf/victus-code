import { Component } from '@angular/core';

@Component({
  selector: 'app-editar-instalacion',
  templateUrl: './editar-instalacion.component.html',
  styleUrls: ['./editar-instalacion.component.scss']
})
export class EditarInstalacionComponent {
  nombre: string = '';
  descripcion: string = '';
  reservable: string = 'no'; // Valor por defecto
  foto: File | null = null;  // Para almacenar la foto cargada

  // Método para manejar el envío del formulario
  onSubmit() {
    // Aquí puedes manejar el envío de los datos
    console.log({
      nombre: this.nombre,
      descripcion: this.descripcion,
      reservable: this.reservable,
      foto: this.foto
    });
  }

  // Método para manejar el cambio de archivo
  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.foto = file;
    }
  }
}

