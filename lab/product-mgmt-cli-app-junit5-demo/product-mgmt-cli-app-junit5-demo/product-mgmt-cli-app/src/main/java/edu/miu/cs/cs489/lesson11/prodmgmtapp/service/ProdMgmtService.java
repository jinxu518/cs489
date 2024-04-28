package edu.miu.cs.cs489.lesson11.prodmgmtapp.service;

import edu.miu.cs.cs489.lesson11.prodmgmtapp.dao.ProductDAO;
import edu.miu.cs.cs489.lesson11.prodmgmtapp.model.Product;

public class ProdMgmtService {
    private ProductDAO productDAO;

    public ProdMgmtService() {
        this.productDAO = new ProductDAO();
    }

    public Product getProductById(Long productId) {
        var products = productDAO.getProducts();
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst().orElse(null);
    }
}
