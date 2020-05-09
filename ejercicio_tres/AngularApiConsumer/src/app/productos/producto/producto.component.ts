import { Component, OnInit } from '@angular/core';
import {ProductoService} from "../../shared/producto.service";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {

  constructor(public service : ProductoService) { }

  ngOnInit(): void {
    this.resetForm();
  }
  resetForm(form? : NgForm){
    if (form!=null)
    form.resetForm();
    this.service.formData = {
      cantidad: null, descripcion: "", id: null, nombre: "", ubicacion: null
    }
  }
  onSubmit(form: NgForm){
    if (form.value.id == null)
    this.insertRecord(form);
    else
      this.updateRecord(form);
}
insertRecord(form: NgForm) {
  this.service.postProducto(form.value).subscribe(res => {
    this.resetForm(form)
    this.service.refreshList();
  });
}
  updateRecord(form : NgForm){
    this.service.putProducto(form.value).subscribe(res => {
      this.resetForm(form)
      this.service.refreshList();
    });
  }

}
