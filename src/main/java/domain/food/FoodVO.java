package domain.food;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FoodVO {
	private Long food_id;
	private String name;
	private int price;
	private Long category_id;

	@Builder
	FoodVO(Long food_id, String name, int price) {
		this.food_id = food_id;
		this.name = name;
		this.price = price;
	}
}
