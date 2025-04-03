package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.infrastructure.ListProductRespository;
import kr.co.hanbit.product.management.presentation.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SimpleProductService {

    private final ListProductRespository listProductRespository;

    private final ModelMapper modelMapper;

    private final ValidationService validationService;

    public ProductDto add(ProductDto productDto) {
        // 1. ProductDto를 Product로 변환하는 코드
        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product);

        // 2. 레포지토리를 호출하는 코드
        Product savedProduct = listProductRespository.add(product);

        // 3. Product를 ProductDto로 변환하는 코드
        ProductDto savedProductDto = modelMapper.map(savedProduct, ProductDto.class);

        // 4. DTO 를 반환하는 코드
        return savedProductDto;
    }

    public ProductDto findById(Long id) {
        Product product = listProductRespository.findById(id);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    public List<ProductDto> findAll() {
        List<Product> products = listProductRespository.findAll();

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtos;

    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = listProductRespository.findByNameContaining(name);

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();

        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updatedProduct = listProductRespository.update(product);
        ProductDto updatedProductDto = modelMapper.map(updatedProduct, ProductDto.class);
        return updatedProductDto;
    }

    public void delete(Long id) {
        listProductRespository.delete(id);
    }
}
