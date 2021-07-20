package fr.nnyimc.billing.repository;
import fr.nnyimc.billing.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, String> {
    List<Bill> findByCustomerId(String customerId);
}
