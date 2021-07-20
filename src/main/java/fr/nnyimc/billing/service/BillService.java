package fr.nnyimc.billing.service;
import fr.nnyimc.billing.dto.*;
import java.util.List;

public interface BillService {
    BillResponseDTO getBill(String id);
    List<BillResponseDTO> listBillsByCustomer(String customerId);
    BillResponseDTO updateBill(BillRequestDTO billRequestDTO);
    BillResponseDTO saveBill(BillRequestDTO billRequestDTO);
    BillResponseDTO deleteBill(BillRequestDTO billRequestDTO);
}
