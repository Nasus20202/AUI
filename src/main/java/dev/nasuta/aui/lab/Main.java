package dev.nasuta.aui.lab;

import dev.nasuta.aui.lab.entity.*;
import dev.nasuta.aui.lab.dto.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class  Main {
    final static int categoryCount = 5;
    final static int productCount = 10;
    final static String fileName = "categories.dat";
    final static int threadCount = Runtime.getRuntime().availableProcessors();
    final static int threadTimeout = 1000;

    public static void main(String[] args) {
        var categories = getCategories();

        printCategories(categories);

        System.out.println("\n\n");

        var products = categories.stream()
                .map(Category::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        products.stream().forEach(System.out::println);

        System.out.println("\n\n");

        products.stream()
                .filter(product -> product.getPrice() > 500)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(System.out::println);

        System.out.println("\n\n");

        var productDtos = products.stream()
                .map(ProductDto::new)
                .sorted()
                .toList();
        productDtos.stream().forEach(System.out::println);

        System.out.println("\n\n");

        try {
            var fileOutputStream = new FileOutputStream(fileName);
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(categories);
            objectOutputStream.flush();
            objectOutputStream.close();

            var fileInputStream = new FileInputStream(fileName);
            var objectInputStream = new ObjectInputStream(fileInputStream);
            var serializedCategories = (List<Category>) objectInputStream.readObject();
            objectInputStream.close();

            printCategories(serializedCategories);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n");

        var threadPool = new ForkJoinPool(threadCount);
        Runnable job = () -> categories.parallelStream().forEach(category ->
            category.getProducts().forEach(product -> {
                var threadName = Thread.currentThread().getName();

                try {
                    Thread.sleep(threadTimeout);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " | " + e.getMessage());
                }

                System.out.println(threadName + " | " + category.getName() + " - " + product.getName());
            }));

        threadPool.submit(job);
        threadPool.close();
    }

    private static List<Category> getCategories() {
        var categories = new ArrayList<Category>();

        IntStream.range(0, Main.categoryCount).forEach(i -> {
            var category = Category.builder()
                    .name("Category " + i)
                    .description("Description " + i)
                    .build();
            categories.add(category);

            var products = new ArrayList<Product>();
            IntStream.range(0, Main.productCount).forEach(j -> {
                var product = Product.builder()
                        .name("Product " + i + "." + j)
                        .brand("Brand " + i + "." + j)
                        .price(j * 100.0)
                        .category(category)
                        .build();
                products.add(product);
            });

            category.setProducts(products);
        });
        return categories;
    }

    private static void printCategories(List<Category> categories) {
        categories.forEach(category -> {
            System.out.println("Category: " + category.getName());
            category.getProducts().forEach(product -> {
                System.out.println("\tProduct: " + product.getName());
            });
        });
    }
}