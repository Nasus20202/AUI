package dev.nasuta.aui.lab.storeservice.util;

import dev.nasuta.aui.lab.storeservice.entity.Category;
import dev.nasuta.aui.lab.storeservice.entity.Product;
import dev.nasuta.aui.lab.storeservice.service.category.CategoryService;
import dev.nasuta.aui.lab.storeservice.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class CliRunner implements CommandLineRunner {
    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public CliRunner(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) {
        var scanner = new Scanner(System.in);
        var isRunning = true;
        while (isRunning) {
            var command = scanner.nextLine();
            switch (command) {
                case "get_categories" -> {
                    categoryService.findAll().forEach(System.out::println);
                }
                case "get_products" -> {
                    productService.findAll().forEach(System.out::println);
                }
                case "get_category_products" -> {
                    System.out.print("Enter category name: ");
                    var categoryName = scanner.nextLine();
                    var category = categoryService.findByName(categoryName).stream().findFirst();
                    if (category.isPresent()) {
                        productService.findByCategory(category.get()).forEach(System.out::println);
                    } else {
                        System.out.println("Category not found");
                    }
                }
                case "create_category" -> {
                    System.out.print("Enter category name: ");
                    var name = scanner.nextLine();
                    System.out.print("Enter category description: ");
                    var description = scanner.nextLine();
                    categoryService.create(Category.builder()
                            .name(name)
                            .description(description)
                            .build());
                    System.out.println("Category created");
                }
                case "delete_category" -> {
                    System.out.print("Enter category name: ");
                    var categoryName = scanner.nextLine();
                    categoryService.findByName(categoryName).forEach(category -> {
                        System.out.println("Deleted category " + category.getName());
                        categoryService.delete(category.getId());
                    });
                }
                case "create_product" -> {
                    System.out.print("Enter product name: ");
                    var name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    var priceStr = scanner.nextLine();
                    System.out.print("Enter product stock: ");
                    var stockStr = scanner.nextLine();
                    System.out.print("Enter product category name: ");
                    var categoryName = scanner.nextLine();

                    int price, stock;
                    try {
                        price = Integer.parseInt(priceStr);
                        stock = Integer.parseInt(stockStr);
                    } catch (InputMismatchException e) {
                        System.out.println("Expected integer values for price and stock");
                        break;
                    }
                    if (price < 0 || stock < 0) {
                        System.out.println("Price and stock must be greater or equal to 0");
                        break;
                    }

                    var category = categoryService.findByName(categoryName).stream().findFirst();
                    if (category.isPresent()) {
                        productService.create(Product.builder()
                                .name(name)
                                .price(price)
                                .stock(stock)
                                .category(category.get())
                                .build());
                        System.out.println("Product created");
                    } else {
                        System.out.println("Category not found");
                    }
                }
                case "delete_product" -> {
                    System.out.print("Enter product category name: ");
                    var categoryName = scanner.nextLine();
                    System.out.print("Enter product name: ");
                    var productName = scanner.nextLine();

                    var category = categoryService.findByName(categoryName).stream().findFirst();
                    if (category.isEmpty()) {
                        System.out.println("Category not found");
                        break;
                    }
                    productService.findByNameAndCategory(productName, category.get()).forEach(product -> {
                        System.out.println("Deleted product " + product.getName() + " from category " + category.get().getName());
                        productService.delete(product.getId());
                    });
                }
                case "help" -> {
                    System.out.println("get_categories - get all categories");
                    System.out.println("get_products - get all products");
                    System.out.println("get_category_products - get all products by category");
                    System.out.println("create_category - create new category");
                    System.out.println("delete_category - delete category");
                    System.out.println("create_product - create new product");
                    System.out.println("delete_product - delete product");
                    System.out.println("help - show help");
                    System.out.println("exit - exit application");
                }
                case "exit" -> {
                    isRunning = false;
                }
                default -> {
                    System.out.println("Unknown command (type 'help' for list of commands)");
                }
            }
        }
    }
}
