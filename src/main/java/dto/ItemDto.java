package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ItemDto {
    private String code;
    private String desc;
    private double unitPrice;
    private int qty;
}
