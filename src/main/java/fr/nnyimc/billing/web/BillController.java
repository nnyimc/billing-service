package fr.nnyimc.billing.web;

import fr.nnyimc.billing.dto.BillRequestDTO;
import fr.nnyimc.billing.dto.BillResponseDTO;
import fr.nnyimc.billing.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api")
public class BillController {
    private BillService billService;

    public BillController( BillService billService) {
        this.billService = billService;
    }

    @GetMapping(path="/billings/{id}")
    public BillResponseDTO getBill(@PathVariable String id) {
        return billService.getBill(id);
    }

    @GetMapping(path="/billings/{customerId}")
    public List<BillResponseDTO> getBillByCustomer(@PathVariable String customerId) {
        return billService.listBillsByCustomer(customerId);
    }

    @PostMapping(path = "/billings")
    public BillResponseDTO saveBill(@RequestBody BillRequestDTO billRequestDTO) {
        return billService.saveBill(billRequestDTO);
    }

    @DeleteMapping(path="/billings")
    public BillResponseDTO deleteBill(@RequestBody BillRequestDTO billRequestDTO) {
        return billService.deleteBill(billRequestDTO);
    }
}
