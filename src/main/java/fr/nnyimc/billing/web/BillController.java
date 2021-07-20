package fr.nnyimc.billing.web;

import fr.nnyimc.billing.dto.*;
import fr.nnyimc.billing.service.BillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping(path = "/api")
public class BillController {
    private final BillService billService;

    public BillController( BillService billService) {
        this.billService = billService;
    }

    @GetMapping(path="/billings/{id}")
    public BillResponseDTO getBill(@PathVariable String id) {
        return billService.getBill(id);
    }

    @GetMapping(path="/billingsBycustomer/{customerId}")
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
