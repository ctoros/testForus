package cl.forus.ejerciciouno.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
