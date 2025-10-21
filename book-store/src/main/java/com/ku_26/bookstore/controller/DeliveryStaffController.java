package com.ku_26.bookstore.controller;

import com.ku_26.bookstore.model.Order;
import com.ku_26.bookstore.model.OrderStatus;
import com.ku_26.bookstore.model.User;
import com.ku_26.bookstore.service.OrderService;
import com.ku_26.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// All URLs in this controller will be prefixed with /delivery-staff
@Controller
@RequestMapping("/delivery-staff")
public class DeliveryStaffController {

    @Autowired
    private OrderService orderService;

    // You will need a way to get the currently logged-in User object.
    // This is a placeholder; you should implement a proper way to fetch it.
    @Autowired
    private UserService userService; // Assuming you have a UserService

    @GetMapping("/dashboard")
    public String showDeliveryDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Get the currently authenticated user's details.
        User currentUser = userService.findByUsername(userDetails.getUsername());
        List<Order> assignedOrders = orderService.getOrdersForDeliveryStaff(currentUser);

        model.addAttribute("orders", assignedOrders);
        model.addAttribute("pageTitle", "My Deliveries");
        return "delivery-dashboard"; // Returns delivery-dashboard.html
    }

    @GetMapping("/orders/{orderId}")
    public String showOrderDetails(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));

        model.addAttribute("order", order);
        model.addAttribute("allStatuses", OrderStatus.values()); // Pass all possible statuses to the view
        return "order-detail"; // Returns order-detail.html
    }

    @PostMapping("/orders/{orderId}/update-status")
    public String updateStatus(@PathVariable Long orderId,
                               @RequestParam OrderStatus status,
                               @RequestParam(required = false) String remarks,
                               RedirectAttributes redirectAttributes) {
        try {
            orderService.updateOrderStatus(orderId, status, remarks);
            // Add a success message that will be displayed on the redirected page.
            redirectAttributes.addFlashAttribute("successMessage", "Order status updated successfully!");
        } catch (Exception e) {
            // Add an error message.
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating status: " + e.getMessage());
        }
        return "redirect:/delivery-staff/dashboard";
    }
}
