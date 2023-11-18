package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String code;
    private String desc;
    private double unitPrice;
    private int qty;
    private JFXButton btn;

    public ItemTm(String code, String desc, double unitPrice, int qty) {
        this.code = code;
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }
}
