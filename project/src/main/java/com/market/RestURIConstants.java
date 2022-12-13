package com.market;

public class RestURIConstants {
    public static final String LOCALHOST = "http://localhost:8080";
    // URI for CUSTOMERS
    public static final String ALL_CUSTOMERS = "/customers";
    public static final String CUSTOMERS_ID = "/customers/{id}";

    // URI for ITEMS
    public static final String CUSTOMERS_ID_ITEMS = "/customers/{customer_id}/items";
    public static final String ITEMS_ID = "/items/{id}";
}
