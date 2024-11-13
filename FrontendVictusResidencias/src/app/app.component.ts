import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { EditarInstalacionModule } from './editar-instalacion/editar-instalacion.module';
import { CrearAdministradorComponent } from './crear-administrador/crear-administrador.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule, 
    RouterOutlet, 
    HeaderComponent, 
    LoginComponent, 
    EditarInstalacionModule,
    CrearAdministradorComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  currentForm: string = '';

  showForm(form: string): void {
    this.currentForm = form;
  }
}
