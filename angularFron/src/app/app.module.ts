import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
// No es necesario importar AppComponent aquí
@NgModule({
  declarations: [],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: []  // Ya no es necesario bootstrap porque AppComponent es standalone
})
export class AppModule { }
