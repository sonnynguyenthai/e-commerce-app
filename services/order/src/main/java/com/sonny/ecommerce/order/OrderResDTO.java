package com.sonny.ecommerce.order;

import java.math.BigDecimal;


public record OrderResDTO
        (
                Integer id,
                String reference,
                BigDecimal amount,
                PaymentMethod paymentMethod,
                String customerId
        )
{

}
