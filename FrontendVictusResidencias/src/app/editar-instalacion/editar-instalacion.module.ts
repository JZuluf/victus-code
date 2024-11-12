// editar-instalacion.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common'; // Asegúrate de importar CommonModule
import { FormsModule } from '@angular/forms'; // Si usas ngModel en el formulario
import { EditarInstalacionComponent } from './editar-instalacion.component'; // Importa tu componente

@NgModule({
  declarations: [EditarInstalacionComponent], // Declara el componente en este módulo
  imports: [
    CommonModule, // Asegúrate de importar CommonModule
    FormsModule // Si usas ngModel, debes importar FormsModule
  ],
  exports: [EditarInstalacionComponent] // Exporte el componente para poder usarlo fuera de este módulo
})
export class EditarInstalacionModule {}
