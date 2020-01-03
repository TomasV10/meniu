//package lt.bit.meniu.models;
//
//import lt.bit.meniu.entities.Product;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductModel {
//    private List<Product>products;
//
//    public ProductModel(){
//        this.products = new ArrayList<Product>();
//        this.products.add(new Product(1, "name 1", 20.25));
//        this.products.add(new Product(2, "name 2", 21.26));
//        this.products.add(new Product(3, "name 3", 22.27));
//    }
//
//    public List<Product>findAll(){
//        return this.products;
//    }
//
//    public Product find(int id){
//        for(Product product : this.products){
//            if(product){
//                return product;
//            }
//        }
//    }
//}
