package edu.miu.cs.cs489.lesson11.prodmgmtapp;

import edu.miu.cs.cs489.lesson11.prodmgmtapp.service.ProdMgmtService;

public class ProdMgmtApp {

    public static void main(String[] args) {
        System.out.println("Hello, JUnit5 and Hamcrest Demo - Starting ProductMgmtApp...");
        var productId = 1L;
        var productFound = new ProdMgmtService()
                .getProductById(productId);
        System.out.printf("Product with Id: %d, %s\n", productId, productFound);
        System.out.println("JUnit5 and Hamcrest Demo ProductMgmtApp completed...Goodbye!");
    }

}
