package G_Aop.A_Aop.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String placeOrder(String pid,int qty)
    {
        try{
            Thread.sleep(1000);
        }catch (Exception e){}

            return "order placed for product "+ pid + "qty"+ qty;

    }
}
