package kr.co.hanbit.product.management.presentation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer amount;
}
