package cl.forus.ejerciciouno.controller;

import cl.forus.ejerciciouno.dto.ProductoDto;
import cl.forus.ejerciciouno.dto.ProductoListResponseDto;
import cl.forus.ejerciciouno.dto.ProductoResponseDto;
import cl.forus.ejerciciouno.service.ProductoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/forus/producto")
public class ProductoController {
    static Logger logger = LogManager.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> saveProducto(@Valid @RequestBody ProductoDto productoDtoRequest) {
        logger.info("Save or Update Mapping, data:"+productoDtoRequest);
        try {
            ProductoDto productoDto = productoService.saveProducto(productoDtoRequest);
            return new ResponseEntity<ProductoDto>(productoDto ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ProductoDto>(HttpStatus.BAD_REQUEST);
        }
    }
        @PutMapping
        public ResponseEntity<ProductoDto> updateProducto(@Valid @RequestBody ProductoDto productoDtoRequest) {
            logger.info("Save or Update Mapping, data:"+productoDtoRequest);
            try {
                ProductoDto productoDto = productoService.update(productoDtoRequest);
                return new ResponseEntity<ProductoDto>(productoDto,HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<ProductoDto>(HttpStatus.BAD_REQUEST);

            }
    }
    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
        public ResponseEntity<List<ProductoDto>> getAllProducto() {
        logger.info("getAll Mapping");
        return new  ResponseEntity<List<ProductoDto>>(productoService.getAllProducto(), HttpStatus.OK);
        }



    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable Long id) {
        logger.info(" getProductById Mapping  Data:"+ id);
        return new ResponseEntity<ProductoDto>(productoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoDto> deleteProductoById(@PathVariable Long id) {
        logger.info("delete Mapping  data:" + id);
        return new ResponseEntity<ProductoDto>(productoService.deleteProductoById(id),HttpStatus.OK);
    }
}
