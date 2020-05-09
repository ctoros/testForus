package cl.forus.ejerciciodos.service;

import cl.forus.ejerciciodos.config.Config;
import cl.forus.ejerciciodos.dto.ProductoDto;
import cl.forus.ejerciciodos.dto.ProductoListResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductoService {
    static Logger logger = LogManager.getLogger(ProductoService.class);

    @Autowired
    Config config;

    @Value("${api.url.rest.api}")
    private String urlApi;

    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<List<ProductoDto>> getAllProducto() {
        //List<ProductoDto> productoDtoList;
        ResponseEntity<List<ProductoDto>> rateResponse = null;
        try {
             rateResponse =
                    restTemplate.exchange(urlApi,
                            HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<ProductoDto>>() {
                            });
            //productoDtoList = rateResponse.getBody();
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return rateResponse;
    }

    public ProductoDto save(ProductoDto productoDto) {
        ProductoDto productoDtoCreated= new ProductoDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDto> requestBody =
                new HttpEntity<>(productoDto, headers);
        try{
            productoDtoCreated = restTemplate.postForObject(urlApi, requestBody, ProductoDto.class);
        }catch (HttpServerErrorException e){
            System.out.println(e.getMessage());
        }
        return productoDtoCreated;
    }

    public ResponseEntity<ProductoDto> update(ProductoDto productoDto) {
        ProductoDto productoDtoUpdated= new ProductoDto();
        ResponseEntity<ProductoDto> rateResponse = null;
        ProductoDto productoDtoUp= new ProductoDto();
        try {
            rateResponse =
                    restTemplate.exchange(urlApi+"/"+productoDto.getId(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<ProductoDto>() {
                            });
           // productoDtoUpdated = rateResponse.getBody();

            if (rateResponse == null){
                return new ResponseEntity<ProductoDto>(HttpStatus.BAD_REQUEST);
            }else{

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<ProductoDto> requestBody =
                        new HttpEntity<>(productoDto, headers);
                try{
                    productoDtoUp = restTemplate.postForObject(urlApi, requestBody, ProductoDto.class);
                }catch (HttpServerErrorException e){
                    System.out.println(e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<ProductoDto>(productoDtoUp,HttpStatus.OK);
    }

    public ResponseEntity<ProductoDto> getById(Long id) {
        ProductoDto productoDtoUpdated= new ProductoDto();
        ResponseEntity<ProductoDto> rateResponse = null;
        ProductoDto productoDtoUp= new ProductoDto();
        try {
            rateResponse =
                    restTemplate.exchange(urlApi+"/"+id,
                            HttpMethod.GET, null, new ParameterizedTypeReference<ProductoDto>() {
                            });
            // productoDtoUpdated = rateResponse.getBody();
            return rateResponse;
        }catch (HttpServerErrorException e){
            System.out.println(e.getMessage());
        }
        return rateResponse;
    }

    public String deleteById(Long id) {
        try{
            restTemplate.delete(urlApi+"/"+id);
            return "Id "+id +"Borrado con exito";
        }catch (HttpServerErrorException e){
            System.out.println(e.getMessage());
            return "Id "+id +"No existe";
        }
    }
}
