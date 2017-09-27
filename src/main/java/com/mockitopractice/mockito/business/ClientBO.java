package com.mockitopractice.mockito.business;

import com.mockitopractice.mockito.business.exception.DifferentCurrenciesException;
import com.mockitopractice.mockito.model.Amount;
import com.mockitopractice.mockito.model.Product;

import java.util.List;


public interface ClientBO {

    Amount getClientProductsSum(List<Product> products)
            throws DifferentCurrenciesException;

}
