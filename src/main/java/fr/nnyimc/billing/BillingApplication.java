package fr.nnyimc.billing;

import fr.nnyimc.billing.dto.BillRequestDTO;
import fr.nnyimc.billing.service.BillService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingApplication.class, args);
    }

    @Bean
    CommandLineRunner start (BillService billService) {
        return args -> {
            billService.saveBill(new BillRequestDTO(BigDecimal.valueOf(2352.01),"C01"));
            billService.saveBill(new BillRequestDTO(BigDecimal.valueOf(4052.00),"C02"));
        };
    }

}
