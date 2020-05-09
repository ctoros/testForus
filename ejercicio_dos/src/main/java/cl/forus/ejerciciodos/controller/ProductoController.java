package cl.forus.ejerciciodos.controller;


import cl.forus.ejerciciodos.dto.ProductoDto;
import cl.forus.ejerciciodos.dto.ProductoListResponseDto;
import cl.forus.ejerciciodos.dto.ProductoResponseDto;
import cl.forus.ejerciciodos.service.ProductoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/forus/producto")
public class ProductoController {
    static Logger logger = LogManager.getLogger(ProductoController.class);

    @Autowired
    ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> saveProducto(@Valid @RequestBody ProductoDto productoDto) {
        logger.info("Save or Update Mapping, data:"+productoDto);
        try {
            ProductoDto productoDtoSave = productoService.save(productoDto);
            return new ResponseEntity<ProductoDto>(productoDtoSave,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProductoDto>(HttpStatus.BAD_REQUEST);
        }
  }
    @PutMapping
    public ResponseEntity<ProductoDto> updateProducto(@Valid @RequestBody ProductoDto productoDtoRequest) {
        logger.info("Save or Update Mapping, data:"+productoDtoRequest);
        try {
            ResponseEntity<ProductoDto> responseUpdate = productoService.update(productoDtoRequest);
            return responseUpdate.getBody() != null ? responseUpdate : null ;
        }catch (Exception e){
            return new ResponseEntity<ProductoDto>(HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
        public ResponseEntity<List<ProductoDto>> getAllProducto() {
        logger.info("getAll Mapping");
        return productoService.getAllProducto();
        }



    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable Long id) {
        logger.info(" getProductById Mapping  Data:"+ id);
        return productoService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductoById(@PathVariable Long id) {
        logger.info("delete Mapping  data:" + id);
        return new ResponseEntity<String>(productoService.deleteById(id),HttpStatus.OK);
    }
}
