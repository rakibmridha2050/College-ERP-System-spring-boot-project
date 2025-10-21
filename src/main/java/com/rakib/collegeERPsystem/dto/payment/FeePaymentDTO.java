package com.rakib.collegeERPsystem.dto.payment;


import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeePaymentDTO {
    private Long id;
    private Long studentFeeId;
    private Double amountPaid;
    private LocalDateTime paymentDate;
    private String paymentMethod; // e.g. Cash, bKash, Card
    private String transactionId;
    private String remarks;
}

