import { Component, OnInit } from '@angular/core';
import {ProductoService} from "../../shared/producto.service";
import {Producto} from "../../shared/producto.model";

@Component({
  selector: 'app-producto-list',
  templateUrl: './producto-list.component.html',
  styleUrls: ['./producto-list.component.css']
})
export class ProductoListComponent implements OnInit {

  constructor(public service : ProductoService) { }

  ngOnInit(): void {
    this.service.refreshList();
  }
  populateForm(prodcuct : Producto){
    this.service.formData = prodcuct;

  }
  onDelete(id : number){
    if (confirm('Seguro que deseas borrar el registro?')){
      this.service.deleteProducto(id).subscribe(res=>{
        this.service.refreshList();
      });
    }
  }

}
