package ru.geekbrains.march.market.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.Product;

import java.util.List;


@Service
@Data
@AllArgsConstructor
public class CartService {

    private final ProductService productService;
    private List<Product> productList;

    public List<Product> getAllProduct() {
        return getProductList();
    }

    public void CartAddProduct(Long id) {
        Product product = findFromDataBase(id);
        productList.add(product);
    }

    public void clearCart() {
        productList.clear();
    }


    private Product findFromDataBase(Long id) {
        return productService.findById(id).orElseThrow();
    }


    public void removeOneProduct(Long id) {
        for (int i = 0; i < productList.size(); i++) {
           Product product = findFromDataBase(id);
            if (productList.get(i).equals(product)){
                productList.remove(i);
            }
        }

    }
}
