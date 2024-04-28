package edu.miu.cs.cs489.lesson11.prodmgmtapp.service;

import edu.miu.cs.cs489.lesson11.prodmgmtapp.model.Product;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdMgmtServiceTest {

    private ProdMgmtService prodMgmtService;

    @BeforeEach
    void setUp() {
        this.prodMgmtService = new ProdMgmtService();
    }

    @AfterEach
    void tearDown() {
        this.prodMgmtService = null;
    }

    @Test
    void test_getProductById() {
        Long productId = 1L;
        var actual = prodMgmtService.getProductById(productId);
        var expected = new Product(1L, "Banana");
        MatcherAssert.assertThat("Test fails: Result does not match",
                actual, CoreMatchers.is(CoreMatchers.equalTo(expected)));
    }

    @Test
    void test_getProductById_where_id_does_not_exist() {
        Long productId = 3L;
        var actual = prodMgmtService.getProductById(productId);
        Product expected = null;
        MatcherAssert.assertThat("Test fails: Result does not match",
                actual, CoreMatchers.is(CoreMatchers.equalTo(expected)));
    }
}