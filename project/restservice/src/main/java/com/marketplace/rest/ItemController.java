package com.marketplace.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.market.RestURIConstants.CUSTOMERS_ID_ITEMS;
import static com.market.RestURIConstants.ITEMS_ID;

@RestController
public class ItemController {
    private final ItemRepository itemRepository;

    private final CustomerRepository customerRepository;

    public ItemController(ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping(CUSTOMERS_ID_ITEMS)
    public ResponseEntity<List<Item>> getAllItemsByCustomerId(@PathVariable Long customer_id) {
        if (!customerRepository.existsById(customer_id)) {
            throw new ResourceNotFoundException("Not found Items with Customer id = " + customer_id);
        }
        List<Item> items = itemRepository.findAllByCustomerId(customer_id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "id") Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Item with id = " + id));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping(CUSTOMERS_ID_ITEMS)
    public ResponseEntity<Item> createItem(@PathVariable(value = "customer_id") Long customerId,
                                                 @RequestBody Item itemRequest) {
        Item item = customerRepository.findById(customerId).map(customer -> {
            itemRequest.setCustomer(customer);
            return itemRepository.save(itemRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Customer with id = " + customerId));

        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping(ITEMS_ID)
    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item itemRequest) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));
        item.setDescription(itemRequest.getDescription());
        item.setPrice(itemRequest.getPrice());
        item.setQuantity(itemRequest.getQuantity());
        item.setTitle(itemRequest.getTitle());
        return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
    }

    @DeleteMapping(ITEMS_ID)
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        itemRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMERS_ID_ITEMS)
    public ResponseEntity<List<Item>> deleteAllItemsOfCustomer(@PathVariable(value = "customer_id") Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResourceNotFoundException("Not found Customer with id = " + customerId);
        }
        itemRepository.deleteByCustomerId(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
