import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { importProvidersFrom } from '@angular/core';
import { AppRoutingModule } from './app/app-routing.module';  // Importa AppRoutingModule aquí

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(AppRoutingModule)  // Asegúrate de importar el enrutamiento aquí
  ]
});
