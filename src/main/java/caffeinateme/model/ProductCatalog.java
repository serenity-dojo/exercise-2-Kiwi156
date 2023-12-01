package caffeinateme.model;

import caffeinateme.model.ProductPrice;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {

    List<ProductPrice> catalog = new ArrayList<>();
    public void addProductsWithPrices(List<ProductPrice> productPrices) {
        catalog.addAll(productPrices);
    }
}
