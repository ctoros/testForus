import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ProductosComponent } from './productos/productos.component';
import { ProductoComponent } from './productos/producto/producto.component';
import { ProductoListComponent } from './productos/producto-list/producto-list.component';
import {ProductoService} from "./shared/producto.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {AppInitService} from "./app.init.service";
import {APP_INITIALIZER} from '@angular/core';

export function init_app(appLoadService: AppInitService) {
  return () => appLoadService.init();
}
@NgModule({
  declarations: [
    AppComponent,
    ProductosComponent,
    ProductoComponent,
    ProductoListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ProductoService,
    AppInitService, {
      provide: APP_INITIALIZER,
      useFactory: init_app,
      deps: [AppInitService],
      multi: true
    },],
  bootstrap: [AppComponent]
})
export class AppModule { }
