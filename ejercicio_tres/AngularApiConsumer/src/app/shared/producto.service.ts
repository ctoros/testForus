import { Injectable } from '@angular/core';
import {Producto} from "./producto.model";
import {environment} from '../../environments/environment';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  public get enviromentMantenedor() {
    return environment.urlMantenedor;
  }
  url = this.enviromentMantenedor;
  formData : Producto;
  list : Producto[];
  constructor(private http : HttpClient) { }

  postProducto(formData : Producto){
    return this.http.post(this.url,formData);
  }

  refreshList(){
    this.http.get(this.url).toPromise().then(res => this.list = res as Producto[] )
  }
  putProducto(formData : Producto){
    return this.http.put(this.url,formData);
  }

  deleteProducto(id: number){
    return this.http.delete(this.url+'/'+id);
  }

}
