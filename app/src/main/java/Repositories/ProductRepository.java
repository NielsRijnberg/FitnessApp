package Repositories;

import java.util.List;

import Classes.Product;
import Contexts.IProductContext;

public class ProductRepository {

    private IProductContext context;

    public ProductRepository(IProductContext context){
        this.context = context;
    }

    public List<Product> HaalAlleProductenOp() {
        return context.HaalAlleProductenOp();
    }
}
