package cl.forus.ejerciciouno.service;

import cl.forus.ejerciciouno.dto.ProductoDto;
import cl.forus.ejerciciouno.dto.ProductoListResponseDto;
import cl.forus.ejerciciouno.dto.ProductoResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ProductoService {

    ProductoDto findById(Long id);

    ProductoDto deleteProductoById(Long id);

    List<ProductoDto> getAllProducto();

    ProductoDto saveProducto(ProductoDto productoDto);

    ProductoDto update(ProductoDto productoDto);
}
