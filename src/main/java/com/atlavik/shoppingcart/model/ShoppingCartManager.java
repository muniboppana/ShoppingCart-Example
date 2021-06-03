package com.atlavik.shoppingcart.model;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ShoppingCartManager {

    private static final String SESSION_KEY_SHOPPING_CART ="shoppingCart";


    public ShoppingCart getShoppingCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(SESSION_KEY_SHOPPING_CART);
        if (shoppingCart == null) {
           shoppingCart = new ShoppingCart();
            setShoppingCart(session,shoppingCart);
        }
        return shoppingCart;
    }

    public void setShoppingCart(HttpSession session, ShoppingCart shoppingCart) {
        session.setAttribute(SESSION_KEY_SHOPPING_CART, shoppingCart);
    }

    public void removeShoppingCart(HttpSession session) {
        session.removeAttribute(SESSION_KEY_SHOPPING_CART);
    }


}
