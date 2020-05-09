package cl.forus.ejerciciouno.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoListResponseDto {
    private List<ProductoDto> productoDtoList;

    public List<ProductoDto> getProductoDtoList() {
        return productoDtoList;
    }

    public void setProductoDtoList(List<ProductoDto> productoDtoList) {
        this.productoDtoList = productoDtoList;
    }

    public ProductoListResponseDto(List<ProductoDto> productoDtoList) {
        this.productoDtoList = productoDtoList;
    }

    @Override
    public String toString() {
        return "ProductoListResponseDto{" +
                "productoDtoList=" + productoDtoList +
                '}';
    }
}
