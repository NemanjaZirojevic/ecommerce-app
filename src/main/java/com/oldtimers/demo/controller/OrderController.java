package com.oldtimers.demo.controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oldtimers.demo.entity.*;
import com.oldtimers.demo.helpermodel.Form;
import com.oldtimers.demo.helpermodel.OrderForm;
import com.oldtimers.demo.repository.OrderDetailsRepository;
import com.oldtimers.demo.repository.OrderRepository;
import com.oldtimers.demo.repository.UserRepository;
import com.oldtimers.demo.service.EmailServiceImpl;
import com.oldtimers.demo.service.StripeService;
import com.oldtimers.demo.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Created by nemanja.zirojevic on 04/05/2020.
 */
@Secured("ROLE_USER")
@RequestMapping(value = "orders")
@Controller
@Slf4j
public class OrderController {

    @Value("${stripe.api.key.public}")
    private String API_PUBLIC_KEY;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    StripeService stripeService;

    @Autowired
    Utility utility;


    @PostMapping(value = "/create")
    public String add(Form form, Model model) {
        List<Products> list = utility.createProductListFromJson(form.getOrders());
        double total = utility.summListItemPrice(list);
        model.addAttribute("products", list);
        model.addAttribute("total", total);
        model.addAttribute("orderForm",new OrderForm());
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "checkout";
    }

    @PostMapping(value = "/checkout")
    public String processOrder(@Valid OrderForm orderForm, Errors errors,Model model){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> currentUser = userRepository.findUserByUserEmail(customUserDetails.getUsername());
        List<Products> products = utility.createProductListFromJson(orderForm.getProducts());
        double total = utility.summListItemPrice(products);
        if(errors.hasErrors())
        {
            model.addAttribute("products", products);
            model.addAttribute("total", total);
            model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
            return "checkout";
        }else {

            for(Products product:products){
                Order order = orderForm.createOrder(currentUser.get());
                orderRepository.save(order);
                product.setOrderDetails(product.getOrderDetailsToSet());
                OrderDetails orderDetails = product.getOrderDetails().get(0);
                if (orderDetails != null) {
                    orderDetails.setOrder(order);
                    orderDetails.setDetailSku("SKU");
                }
                orderDetailsRepository.save(orderDetails);
            }
            emailService.sendOrderToEmail(orderForm);
        }
        if(orderForm.isCardPayment()) {
            String chargeId = stripeService.createCharge(orderForm.getOrderEmail(), orderForm.getToken(), orderForm.getOrderAmount());
            if (chargeId == null) {
                model.addAttribute("products", products);
                model.addAttribute("total", total);
                model.addAttribute("orderForm", new OrderForm());
                model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
                return "checkout";
            }
        }
        return "redirect:/orders/success";
    }

}
