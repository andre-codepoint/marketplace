package com.marketplace.client.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumingRestApplication {

//    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

//    @GetMapping(HOME)
//    public String goHome(final Model model) {
//        return "home";
//    }

//    @GetMapping(HOME)
//    public ModelAndView goHome() {
//        ModelAndView modelAndView = new ModelAndView("home");
//        return modelAndView;
//    }
//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//        return args -> {
//            CustomerUI customerUI = restTemplate.getForObject(LOCALHOST+"/customers/1", CustomerUI.class);
//            log.info(customerUI.toString());
//        };
//    }
}
