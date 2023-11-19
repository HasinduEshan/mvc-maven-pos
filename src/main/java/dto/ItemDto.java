package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ItemDto {
    private String code;
    private String desc;
    private double unitPrice;
    private int qty;
}
