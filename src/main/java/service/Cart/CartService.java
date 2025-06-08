package service.Cart;

import domain.OrderItemVO;
import domain.food.FoodVO;

import java.util.List;

public interface CartService {

    void addToCart(FoodVO food);

    void printCart();

    List<OrderItemVO> getCartItems();

    void clearCart();

    void removeFromCart(int index);

}
