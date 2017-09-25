package Contexts;

import java.util.List;

import Classes.Product;

public interface IProductContext {

    List<Product> HaalAlleProductenOp();

    void KoopProduct(Product product);
}
