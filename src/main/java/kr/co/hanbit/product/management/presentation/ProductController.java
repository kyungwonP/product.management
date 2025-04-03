package kr.co.hanbit.product.management.presentation;

import kr.co.hanbit.product.management.application.SimpleProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final SimpleProductService simpleProductService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return simpleProductService.add(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return simpleProductService.findById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<ProductDto> findProducts(@RequestParam(required = false, name = "name") String name) {
        if (null == name) {
            return simpleProductService.findAll();
        }
        return simpleProductService.findByNameContaining(name);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ProductDto findProducts(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return simpleProductService.update(productDto);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void findProducts(@PathVariable("id") Long id) {
        simpleProductService.delete(id);
    }
}
