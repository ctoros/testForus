package cl.forus.ejerciciodos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;

@JsonRootName(value = "")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoResponseDto {
    private ProductoDto productoDto;
    public ProductoResponseDto(ProductoDto productoDto) {
        this.productoDto = productoDto;

    }

    public ProductoDto getProductoDto() {
        return productoDto;
    }

    public void setProductoDto(ProductoDto productoDto) {
        this.productoDto = productoDto;
    }

    @Override
    public String toString() {
        return "ProductoResponseDto{" +
                "productoDto=" + productoDto +
                '}';
    }
}
