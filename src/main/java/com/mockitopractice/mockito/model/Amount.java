package com.mockitopractice.mockito.model;

import java.math.BigDecimal;

public interface Amount {
    BigDecimal getValue();

    Currency getCurrency();
}
