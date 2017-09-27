package com.mockitopractice;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mockitopractice.mockito.business.ClientBO;
import com.mockitopractice.mockito.business.exception.DifferentCurrenciesException;
import com.mockitopractice.mockito.business.impl.ClientBOImpl;
import com.mockitopractice.mockito.model.Amount;
import com.mockitopractice.mockito.model.Currency;
import com.mockitopractice.mockito.model.Product;
import com.mockitopractice.mockito.model.ProductType;
import com.mockitopractice.mockito.model.impl.AmountImpl;
import com.mockitopractice.mockito.model.impl.ProductImpl;
import org.junit.Test;

public class ClientBOTestRefactored {

    private ClientBO clientBO = new ClientBOImpl();

    @Test
    public void testClientProductSum_AllProductsSameCurrency()
            throws DifferentCurrenciesException {

        Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO) };

        Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);

        List<Product> products = createProductListWithAmounts(amounts);

        Amount actual = clientBO.getClientProductsSum(products);

        assertAmount(actual, expected);
    }

    @Test(expected = DifferentCurrenciesException.class)
    public void testClientProductSum_DifferentCurrencies_ThrowsException()
            throws DifferentCurrenciesException {

        Amount[] amounts = {
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO),
                new AmountImpl(new BigDecimal("6.0"), Currency.INDIAN_RUPEE) };

        List<Product> products = createProductListWithAmounts(amounts);

        @SuppressWarnings("unused")
        Amount actual = clientBO.getClientProductsSum(products);

    }

    @Test
    public void testClientProductSum_NoProducts()
            throws DifferentCurrenciesException {

        Amount[] amounts = {};
        Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);

        List<Product> products = createProductListWithAmounts(amounts);

        Amount actual = clientBO.getClientProductsSum(products);


        assertAmount(actual, expected);
    }

    private void assertAmount(Amount actual, Amount expected) {
        assertEquals(expected.getCurrency(), actual.getCurrency());
        assertEquals(expected.getValue(), actual.getValue());
    }

    private List<Product> createProductListWithAmounts(Amount[] amounts) {
        List<Product> products = new ArrayList<Product>();
        for (Amount amount : amounts) {
            products.add(new ProductImpl(100, "Product 15",
                    ProductType.BANK_GUARANTEE, amount));
        }
        return products;
    }

}
