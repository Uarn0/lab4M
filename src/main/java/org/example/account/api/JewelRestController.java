package org.example.account.api;

import org.example.account.api.dto.ClientDto;
import org.example.account.api.dto.OrderDto;
import org.example.account.api.dto.ProductDto;
import org.example.account.repository.model.Client;
import org.example.account.repository.model.Order;
import org.example.account.service.JewelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class JewelRestController {

    @Autowired
    private JewelService jewelService;

    @GetMapping(path = "/products", produces = "application/json")
    public List<ProductDto> getAllProducts() {
        return jewelService.getProducts().stream()
                .map(p -> ProductDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping(path = "/clients", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Client> createClient(@RequestBody ClientDto dto) {
        Client client = jewelService.registerClient(dto);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @PostMapping(path = "/orders", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        try {
            Order order = jewelService.processOrder(orderDto);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(path = "/auth/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody org.example.account.api.dto.ClientDto loginDto) {
        return ResponseEntity.ok("fake-jwt-token-12345");
    }

    @GetMapping(path = "/admin/analytics", produces = "application/json")
    public ResponseEntity<String> getAnalytics(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null) {
            return new ResponseEntity<>("Доступ заборонено", HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok("{\"totalSales\": 23500.0}");
    }
}