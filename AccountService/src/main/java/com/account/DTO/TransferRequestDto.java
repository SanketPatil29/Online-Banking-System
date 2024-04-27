package com.account.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
public class TransferRequestDto {
    private Long senderAccountId;
    private Long recipientAccountId;
    private BigDecimal amount;
}
