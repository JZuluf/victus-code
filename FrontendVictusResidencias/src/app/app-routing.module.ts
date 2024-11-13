import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { EditarInstalacionComponent } from './editar-instalacion/editar-instalacion.component';
import { CrearAdministradorComponent } from './crear-administrador/crear-administrador.component';

const routes: Routes = [
  { path: 'crear-administrador', component: CrearAdministradorComponent },
  { path: 'editar-instalacion', component: EditarInstalacionComponent },
  { path: 'entrar', component: LoginComponent },
  { path: '', redirectTo: 'entrar', pathMatch: 'full' }, // Ruta por defecto
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

