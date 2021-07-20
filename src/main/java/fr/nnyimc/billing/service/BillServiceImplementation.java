package fr.nnyimc.billing.service;

import fr.nnyimc.billing.dto.*;
import fr.nnyimc.billing.entities.Bill;
import fr.nnyimc.billing.exceptions.CustomerNotFoundException;
import fr.nnyimc.billing.mappers.BillMapper;
import fr.nnyimc.billing.models.Customer;
import fr.nnyimc.billing.repository.BillRepository;
import fr.nnyimc.billing.restClient.CustomerRestClient;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BillServiceImplementation implements BillService {
    private final BillMapper billMapper;
    private final BillRepository billRepository;
    private final CustomerRestClient customerRestClient;
    private Customer customer;

    public BillServiceImplementation(BillMapper billMapper,
                                     BillRepository billRepository,
                                     CustomerRestClient customerRestClient) {
        this.billMapper = billMapper;
        this.billRepository = billRepository;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public BillResponseDTO getBill(String id) {
        Bill bill = billRepository.getById(id);
        Customer customer = customerRestClient.getCustomer(bill.getCustomerId());
        bill.setCustomer(customer);
        return billMapper.billToBillResponseDTO(bill);
    }

    @Override
    public List<BillResponseDTO> listBillsByCustomer(String customerId) {
        List<Bill> allCustomerBills = billRepository.findByCustomerId(customerId);
        return allCustomerBills
                .stream()
                .map(billMapper::billToBillResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillResponseDTO updateBill(BillRequestDTO billRequestDTO) {
        Bill updatedBill = billMapper.billRequestDTOToBill(billRequestDTO);
        Customer customer = customerRestClient.getCustomer(updatedBill.getCustomerId());
        updatedBill.setCustomer(customer);
        billRepository.save(updatedBill);
        return billMapper.billToBillResponseDTO(updatedBill);
    }

    @Override
    public BillResponseDTO saveBill(BillRequestDTO billRequestDTO) {
        Bill addedBill = billMapper.billRequestDTOToBill(billRequestDTO);
        addedBill.setId(UUID.randomUUID().toString());
        addedBill.setDate(new Date());
        try {
            customer = customerRestClient.getCustomer(addedBill.getCustomerId());
        } catch (Exception e) {
             throw new CustomerNotFoundException("The provided customer doesn't exist!!");
        }
        billRepository.save(addedBill);
        addedBill.setCustomer(customer);
        return billMapper.billToBillResponseDTO(addedBill);
    }

    @Override
    public BillResponseDTO deleteBill(BillRequestDTO billRequestDTO) {
        Bill deletedBill = billMapper.billRequestDTOToBill(billRequestDTO);
        billRepository.delete(deletedBill);
        return billMapper.billToBillResponseDTO(deletedBill);
    }
}
