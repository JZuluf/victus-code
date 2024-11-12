import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';  // Asegúrate de importar CommonModule
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { IconoComponent} from './icono/icono.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [IconoComponent,CommonModule, RouterOutlet, HeaderComponent, LoginComponent,], // Asegúrate de agregar CommonModule aquí
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


