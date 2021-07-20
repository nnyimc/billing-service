package fr.nnyimc.billing.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {
    private BigDecimal amount;
    private String customerId;
}
