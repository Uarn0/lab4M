package org.example.account.service;
import org.example.account.BusinessException;
import org.example.account.ResourceNotFoundException;
import org.example.account.api.dto.CartItemDto;
import org.example.account.api.dto.ClientDto;
import org.example.account.api.dto.OrderDto;
import org.example.account.repository.*;
import org.example.account.repository.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JewelService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addToCart(CartItemDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Клієнта не знайдено"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Товар не знайдено"));

        CartItem item = CartItem.builder()
                .client(client)
                .product(product)
                .quantity(dto.getQuantity())
                .build();

        return cartItemRepository.save(item);
    }

    @PostConstruct
    public void initData() {
        if (productRepository.count() == 0) {
            productRepository.save(Product.builder()
                    .name("Каблучка з діамантом")
                    .price(15000.0)
                    .stockQuantity(10)
                    .build());

            productRepository.save(Product.builder()
                    .name("Золотий ланцюжок")
                    .price(8500.0)
                    .stockQuantity(5)
                    .build());
        }
    }

    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }

    @Transactional
    public Order processOrder(OrderDto request) {
        if (!processBankTransaction(request.getCardNumber(), request.getCvv())) {
            throw new BusinessException("Оплату відхилено");
        }

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Клієнта не знайдено"));

        Order order = Order.builder()
                .client(client)
                .totalAmount(request.getAmount())
                .status(OrderStatus.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

    @Transactional
    public Client registerClient(ClientDto dto) {
        Client client = Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .bonusBalance(0.0)
                .totalOrders(0)
                .build();
        return clientRepository.save(client);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    private boolean processBankTransaction(String cardNumber, String cvv) {
        return cardNumber != null && !cardNumber.isEmpty() && cvv != null && !cvv.isEmpty();
    }
}