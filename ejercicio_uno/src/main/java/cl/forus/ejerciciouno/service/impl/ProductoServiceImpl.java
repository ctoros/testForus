package cl.forus.ejerciciouno.service.impl;

import cl.forus.ejerciciouno.dto.ProductoDto;
import cl.forus.ejerciciouno.dto.ProductoListResponseDto;
import cl.forus.ejerciciouno.dto.ProductoResponseDto;
import cl.forus.ejerciciouno.model.Producto;
import cl.forus.ejerciciouno.repository.ProductoRepository;
import cl.forus.ejerciciouno.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {
    static Logger logger = LogManager.getLogger(ProductoServiceImpl.class);

    private final ObjectMapper objectMapper;
    private ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ObjectMapper objectMapper) {
        this.productoRepository = productoRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public ProductoDto findById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            ProductoDto productoDto = objectMapper.convertValue(producto.get(), ProductoDto.class);
            return productoDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @Override
    public ProductoDto deleteProductoById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            ProductoDto productoDto = objectMapper.convertValue(producto.get(), ProductoDto.class);
            productoRepository.delete(producto.get());
            return productoDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @Override
    public List<ProductoDto> getAllProducto() {
        Optional<List<Producto>> productoList = Optional.ofNullable(productoRepository.findAll());
        if (productoList.isPresent()) {
            List<ProductoDto> productoDto = productoList.get()
                    .stream().map(producto -> objectMapper.convertValue(producto, ProductoDto.class))
                    .collect(Collectors.toList());
            return productoDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Override
    public ProductoDto saveProducto(ProductoDto productoDto) {
        Producto producto = objectMapper.convertValue(productoDto, Producto.class);
        producto = productoRepository.save(producto);
        ProductoDto convertedProductoDto = objectMapper.convertValue(producto, ProductoDto.class);
        return convertedProductoDto;
    }

    @Override
    public ProductoDto update(ProductoDto productoDto) {
        Optional<Producto> producto = productoRepository.findById(productoDto.getId());
        if (producto.isPresent()) {
            Producto productoSave = objectMapper.convertValue(productoDto, Producto.class);
            productoSave = productoRepository.save(productoSave);
            ProductoDto convertedProductoDto = objectMapper.convertValue(producto, ProductoDto.class);
            return convertedProductoDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
