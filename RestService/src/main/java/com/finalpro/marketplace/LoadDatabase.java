package com.finalpro.marketplace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, ItemRepository itemRepository) {

        return args -> {
            Customer emp01=new Customer("Little Bob", Role.USER);
            Item item01 = new Item( "iPhone", "Super magic iPhone 8g with custom case", 699, 3, emp01);
            Item item02 =new Item("Android Smartphone", "All day all weather for everybody Android Phone", 199, 4, emp01);
            Item item03 =new Item("Nokia 3310", "Oldschool 1g phone for hipster", 100, 1, emp01);
            customerRepository.save(emp01);
            itemRepository.save(item01);
            itemRepository.save(item02);
            itemRepository.save(item03);

        };
    }
}
