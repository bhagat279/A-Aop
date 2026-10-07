package G_Aop.A_Aop.controller;

import G_Aop.A_Aop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public String placeOrder()
    {
      return   orderService.placeOrder("mobile",5);
    }
}
