package sample.cafekiosk.spring.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

/**
 * Spring 환경에서의 통합테스트
 * Persistent Layer 테스트는 단위테스트에 가까움
 * DB에 엑세스 하는 계층
 */

//@SpringBootTest
@ActiveProfiles("test") //프로파일 지정 (sql.data 사용 x)
@DataJpaTest    //SpringBoot 테스트보다 가벼움 - JPA 관련된 Bean들만 주입을 해줘서 서버를 띄워줌
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBySellingStatusIn(){
        // given
        Product product1 = Product.builder()
                .productNumber("001")
                .type(ProductType.HANDMADE)
                .sellingStatus(SELLING)    //판매중
                .name("아메리카노")
                .price(4000)
                .build();
        Product product2 = Product.builder()
                .productNumber("002")
                .type(ProductType.HANDMADE)
                .sellingStatus(HOLD)   //판매 보류
                .name("카페라떼")
                .price(4500)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(ProductType.HANDMADE)
                .sellingStatus(STOP_SELLING)   //판매 중지
                .name("팥빙수")
                .price(7000)
                .build();

        productRepository.saveAll(List.of(product1,product2,product3));

        // when
        List<Product> products = productRepository.findAllBySellingStatusIn(
                List.of(SELLING, HOLD));

        //then
        assertThat(products).hasSize(2) //사이즈 검증
                .extracting("productNumber","name","sellingStatus") //검증하고자 하는 필드만 추출
                .containsExactlyInAnyOrder( //순서상관없이 데이터 체크
                        tuple("001","아메리카노",SELLING),
                        tuple("002","카페라떼",HOLD)
                );
    }
}