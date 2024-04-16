package sample.cafekiosk.spring.api.controller.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import sample.cafekiosk.spring.api.service.order.OrderService;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    //Todo: 주문 생성 api를 생성한다.
}
