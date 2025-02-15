import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { EditarInstalacionComponent } from './editar-instalacion/editar-instalacion.component';
import { LoginComponent } from './login/login.component';
import { CrearAdministradorComponent } from './crear-administrador/crear-administrador.component';


const routes: Routes = [
  { 
    path: 'header', 
    component: HeaderComponent
  },
  { 
    path: 'crear-administrador', 
    component: CrearAdministradorComponent
  },
  { 
    path: 'nueva-zona-comun', 
    component: EditarInstalacionComponent
  },
  { 
    path: 'entrar', 
    component: LoginComponent
  },
  { 
    path: '', 
    component: LoginComponent 
  },
  { 
    path: '**', 
    redirectTo: '' // Redirecciona a la página de inicio si no se encuentra una ruta
  }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

