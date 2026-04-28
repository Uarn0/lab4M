package org.example.account.service;
import org.example.account.api.dto.ClientDto;
import org.example.account.api.dto.OrderDto;
import org.example.account.repository.ClientRepository;
import org.example.account.repository.OrderRepository;
import org.example.account.repository.ProductRepository;
import org.example.account.repository.model.Client;
import org.example.account.repository.model.Order;
import org.example.account.repository.model.Product;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JewelService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void initData() {
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder().name("Каблучка з діамантом").price(15000.0).build());
            productRepository.save(Product.builder().name("Золотий ланцюжок").price(8500.0).build());
        }
    }

    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public Client registerClient(ClientDto dto) {
        Client client = Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .bonusPoints(0)
                .build();
        return clientRepository.save(client);
    }

    @Transactional
    public Order processOrder(OrderDto request) {
        boolean paymentSuccess = processBankTransaction(request.getCardNumber(), request.getCvv());

        if (!paymentSuccess) {
            throw new RuntimeException("Оплату відхилено");
        }

        Order order = Order.builder()
                .clientId(request.getClientId())
                .totalAmount(request.getAmount())
                .status("PAID")
                .build();

        return orderRepository.save(order);
    }

    private boolean processBankTransaction(String cardNumber, String cvv) {
        return cardNumber != null && !cardNumber.isEmpty() && cvv != null && !cvv.isEmpty();
    }
}