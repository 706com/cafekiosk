package sample.cafekiosk.unit.beverage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AmericanoTest {

    @Test
    void getName(){
        Americano americano = new Americano();

//        assertEquals(americano.getName(),"아메리카노");  //Junit 프레임워크를 사용한 테스트
        assertThat(americano.getName()).isEqualTo("아메리카노");   //assertJ 라이브러리를 사용한 테스트 -> 좀 더 명시적이다.

    }
    @Test
    void getPrice(){
        Americano americano = new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }
}