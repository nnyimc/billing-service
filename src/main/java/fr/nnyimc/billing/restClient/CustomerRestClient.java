package fr.nnyimc.billing.restClient;

import fr.nnyimc.billing.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@PathVariable String id);

    @GetMapping(path = "/api/customers")
    List<Customer> listCustomers();
}
