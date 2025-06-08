package service.Cart;

import domain.OrderItemVO;
import domain.food.FoodVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CartServiceImpl 단위 테스트")
class CartServiceImplTest {

    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl();
    }

    // ---------- 헬퍼 메서드 ----------
    private FoodVO createFood(Long id, String name, int price) {
        return FoodVO.builder()
                .food_id(id)
                .name(name)
                .price(price)
                .build();
    }
    // --------------------------------

    @Test
    @DisplayName("1) 새 음식 담기: 장바구니에 항목 1개 추가")
    void testAddToCart_NewItem() {
        FoodVO burger = createFood(1L, "Burger", 5000);

        cartService.addToCart(burger);

        List<OrderItemVO> items = cartService.getCartItems();
        assertEquals(1, items.size());
        assertEquals("Burger", items.get(0).getFoodName());
        assertEquals(1, items.get(0).getQuantity());
        assertEquals(5_000, items.get(0).getSubTotal());
    }

    @Test
    @DisplayName("2) 동일 음식 두 번 담기: 수량과 소계 증가")
    void testAddToCart_DuplicateItem() {
        FoodVO pizza = createFood(2L, "Pizza", 8000);

        cartService.addToCart(pizza); // 1개
        cartService.addToCart(pizza); // 2개로 증가

        List<OrderItemVO> items = cartService.getCartItems();
        assertEquals(1, items.size());                    // 항목은 1개
        OrderItemVO item = items.get(0);
        assertEquals(2, item.getQuantity());              // 수량 2
        assertEquals(16_000, item.getSubTotal());         // 8,000 * 2
    }

    @Test
    @DisplayName("3) removeFromCart: 정상 삭제 후 크기 감소")
    void testRemoveFromCart() {
        cartService.addToCart(createFood(1L, "Burger", 5000));
        cartService.addToCart(createFood(2L, "Pizza", 8000));

        cartService.removeFromCart(0);                    // 첫 번째 항목 삭제

        assertEquals(1, cartService.getCartItems().size());
        assertEquals("Pizza", cartService.getCartItems().get(0).getFoodName());
    }

    @Test
    @DisplayName("4) clearCart: 장바구니 초기화")
    void testClearCart() {
        cartService.addToCart(createFood(1L, "Burger", 5000));
        cartService.clearCart();

        assertTrue(cartService.getCartItems().isEmpty());
        assertEquals(0, cartService.getTotalPrice());
    }

    @Test
    @DisplayName("5) getTotalPrice: 총액 계산 정확성")
    void testGetTotalPrice() {
        cartService.addToCart(createFood(1L, "Burger", 5000)); // 1개
        cartService.addToCart(createFood(1L, "Burger", 5000)); // 2개
        cartService.addToCart(createFood(2L, "Drink", 2000));  // 1개

        assertEquals(12_000, cartService.getTotalPrice());     // 5,000*2 + 2,000
    }

    @Test
    @DisplayName("6) printCart: 콘솔 출력 내용 확인(간단)")
    void testPrintCartOutput() {
        cartService.addToCart(createFood(1L, "Burger", 5000));

        // System.out 캡처
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        cartService.printCart();

        System.setOut(originalOut);

        String output = out.toString();
        assertTrue(output.contains("🛍 담은 상품"));
        assertTrue(output.contains("Burger"));
        assertTrue(output.contains("5,000원"));
    }
}
