package service.Cart;

import domain.OrderItemVO;
import domain.food.FoodVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final List<OrderItemVO> cart = new ArrayList<>();

    @Override
    public void addToCart(FoodVO food) {
        for (OrderItemVO item : cart) {
            if (item.getFoodId() == food.getFood_id()) {
                int newQty = item.getQuantity() + 1;
                item.setQuantity(newQty);
                item.setSubTotal(newQty * item.getPriceAtOrder());
                System.out.println("🛒 " + food.getName() + " 수량 증가");
                return;
            }
        }

        OrderItemVO item = OrderItemVO.builder()
                .foodId(food.getFood_id())
                .foodName(food.getName())
                .quantity(1)
                .priceAtOrder(food.getPrice())
                .subTotal(food.getPrice())
                .build();

        cart.add(item);
        System.out.println("🛒 " + food.getName() + " 담김");
    }

    @Override
    public void printCart() {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n🛍 담은 상품");
        for (int i = 0; i < cart.size(); i++) {
            OrderItemVO item = cart.get(i);
            System.out.printf("%d. %s | 수량: %d개 | 단가: %,d원 | 소계: %,d원 (취소)%n",
                    i + 2, item.getFoodName(), item.getQuantity(),
                    item.getPriceAtOrder(), item.getSubTotal());
        }

        System.out.println("\n총액 : " + String.format("%,d원", getTotalPrice()));
    }

    @Override
    public List<OrderItemVO> getCartItems() {
        return Collections.unmodifiableList(cart); // 외부 수정 방지
    }

    @Override
    public void clearCart() {
        cart.clear();
    }

    @Override
    public void removeFromCart(int index) {
        if (index < 0 || index >= cart.size()) {
            System.out.println("⚠️ 잘못된 번호입니다.");
            return;
        }
        OrderItemVO removed = cart.remove(index);
        System.out.println("❌ " + removed.getFoodName() + " 삭제되었습니다.");
    }

    public int getTotalPrice() {
        return cart.stream()
                .mapToInt(OrderItemVO::getSubTotal)
                .sum();
    }
}
