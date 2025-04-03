package kr.co.hanbit.product.management.infrastructure;

import kr.co.hanbit.product.management.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ListProductRespository {

    private List<Product> products = new CopyOnWriteArrayList<>(); // 동시성 문제로 부터 ArrayList 보다 CopyOnWriteArrayList 가 조금 더 나음

    private AtomicLong sequence = new AtomicLong(1L); // 동시성 문제로 인해 구현

    public Product add(Product product) {
        product.setId(sequence.getAndAdd(1L));

        products.add(product);
        return product;
    }

    public Product findById(Long id) {
        return products.stream()
                .filter(product -> product.sameId(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Product> findAll() {
        return products;
    }

    public List<Product> findByNameContaining(String name) {
        return products.stream()
                .filter(product -> product.containsName(name))
                .toList();
    }

    public Product update(Product product) {
        Integer indexToModify = products.indexOf(product);
        products.set(indexToModify, product);
        return product;
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        products.remove(product);
    }
}
