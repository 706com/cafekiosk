package sample.cafekiosk.spring.api.service.product;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.Product;
import sample.cafekiosk.spring.domain.ProductRepository;
import sample.cafekiosk.spring.domain.ProductSellingType;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts(){
        List<Product> products = productRepository.findAllBySellingTypeIn(ProductSellingType.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
