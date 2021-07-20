package fr.nnyimc.billing.dto;

import fr.nnyimc.billing.models.Customer;
import lombok.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseDTO {
    private String id;
    private Date date;
    private BigDecimal amount;
    private Customer customer;
}
