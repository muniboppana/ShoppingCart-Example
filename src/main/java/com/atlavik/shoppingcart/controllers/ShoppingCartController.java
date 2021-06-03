package com.atlavik.shoppingcart.controllers;

import com.atlavik.shoppingcart.exception.ProductNotFoundException;
import com.atlavik.shoppingcart.model.Product;
import com.atlavik.shoppingcart.model.ShoppingCart;
import com.atlavik.shoppingcart.model.ShoppingCartManager;
import com.atlavik.shoppingcart.repository.ProductRepository;
import com.atlavik.shoppingcart.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/carts")

public class ShoppingCartController {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingCartManager shoppingCartManager;
    @Autowired
    HttpSession session;

    /**
     * create a ShoppingCart
     * @param shoppingCart
     * @return
     */

    @GetMapping("/createShoppingCart")
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart){
       List<Product> productList =  shoppingCart.getProducts();
        if(!productList.isEmpty()){
            shoppingCart.getProducts().addAll(productList);
        }
       return new ResponseEntity<>(shoppingCartRepository.save(shoppingCart),HttpStatus.CREATED);

    }

    /**
     * add a products into ShoppingCart
     * @param product
     * @return
     */
 @PostMapping(value = "/addProduct")
    public ResponseEntity<ShoppingCart>  addProductShoppingCart(@RequestBody Product product) {
        try{
            ShoppingCart shoppingCart = shoppingCartManager.getShoppingCart(session);
            shoppingCart.getProducts().add(product);
            return new ResponseEntity<>(shoppingCartRepository.save(shoppingCart),HttpStatus.CREATED);
        }catch(Exception exp){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }

 }

    @PostMapping(value = "/createProduct")
    public ResponseEntity<ShoppingCart>  addProductsInToShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        try{
            List<Product>  products = shoppingCart.getProducts();
            shoppingCart.addProductToShoppingCart(products);
            return new ResponseEntity<>(shoppingCartRepository.save(shoppingCart),HttpStatus.CREATED);

        }catch(Exception exp){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * fetch products from Shopping Cart
     * @param cartId
     * @return
     */
    @GetMapping(value = "/products/{cartId}")
    public ResponseEntity<List<Product>> getProductFromShoppingCart(@PathVariable("cartId") Long cartId) {
        try  {

          Optional<ShoppingCart> shoppingCart =  shoppingCartRepository.findById(cartId);
          System.out.println("cartId value is"+cartId);
            System.out.println("cartId value is"+shoppingCart.get().getCartId());
            if(shoppingCart.isPresent()){
                 List<Product>  products =   shoppingCart.get().getProducts();
                    return new ResponseEntity<>(products, HttpStatus.OK);
                }

        }catch(Exception exp ){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    /**
     * update the product to ShoppingCart
     * @param cartId
     * @param modifyProduct
     * @return
     */

    @PutMapping(value="/updateProduct/{cartId}")
    public ResponseEntity<Product>  updateProduct(@PathVariable("cartId") Long cartId, @RequestBody Product modifyProduct) {

        try {
            Product updatedProduct = null;
           Optional<ShoppingCart> shoppingCart =  shoppingCartRepository.findById(cartId);
           boolean isProductPrsnt = false;
           if(shoppingCart.isPresent()){
              List<Product>  productList = shoppingCart.get().getProducts();
              if(productList.isEmpty()) {
                  return  new ResponseEntity(HttpStatus.NOT_FOUND);
              }
              for(Product product : productList){
                  if(product.getId().equals(modifyProduct.getId())){
                      isProductPrsnt = true;
                      product.setDescription(modifyProduct.getDescription());
                      product.setPrice(modifyProduct.getPrice());
                      product.setCategory(modifyProduct.getCategory());
                      updatedProduct =  productRepository.save(product);
                      break;
                  }
              }

              if(!isProductPrsnt){
                  return (ResponseEntity<Product>) ResponseEntity.notFound();
              } else {
                  return ResponseEntity.ok(updatedProduct);
              }
           }  else {
               return  new ResponseEntity(HttpStatus.NOT_FOUND);
           }
        }catch(Exception exp){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  Delete a product from shopping cart
     * @param cartId
     * @return
     */
    @DeleteMapping(value = "/deleteProduct/{cartId}")

    public ResponseEntity deleteProduct(@PathVariable(value = "cartId") Long  cartId){

            shoppingCartRepository.deleteById(cartId);
            return ResponseEntity.ok("Product is deleted successfully" + cartId);

    }

    /**
     * Get shopping carts from data base.
     * @return
     */
    @GetMapping("")

    public ResponseEntity<List<ShoppingCart>>  getShoppingCart(){

        List<ShoppingCart> shoppingCart = shoppingCartRepository.findAll();
        if(!shoppingCart.isEmpty()){
            return new ResponseEntity<>(shoppingCart,HttpStatus.ACCEPTED);
        } else {
            return (ResponseEntity<List<ShoppingCart>>) ResponseEntity.notFound();
        }
    }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)

    public ResponseEntity<String>  handlesProductNotFoundException(ProductNotFoundException exception) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }



}
