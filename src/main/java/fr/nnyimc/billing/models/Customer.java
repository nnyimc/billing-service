package fr.nnyimc.billing.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private String email;
    private String name;
}
