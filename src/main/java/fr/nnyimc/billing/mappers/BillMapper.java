package fr.nnyimc.billing.mappers;

import fr.nnyimc.billing.dto.*;
import fr.nnyimc.billing.entities.Bill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    Bill billRequestDTOToBill(BillRequestDTO billRequestDTO);
    BillResponseDTO billToBillResponseDTO(Bill bill);
}
