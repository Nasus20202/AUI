package dev.nasuta.aui.lab;

import dev.nasuta.aui.lab.entity.*;
import dev.nasuta.aui.lab.dto.*;
import dev.nasuta.aui.lab.entity.Product;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class  Main {
    final static String fileName = "products.dat";
    final static int threadCount = Runtime.getRuntime().availableProcessors();
    final static int threadTimeout = 1000;

    public static void main(String[] args) {
        // Task 2
        System.out.println("2.");

        var categories = List.of(
                Category.builder().name("Fruits").description("Fresh and tasty fruits").build(),
                Category.builder().name("Vegetables").description("Healthy vegetables").build(),
                Category.builder().name("Diary").description("Milk and yoghurts").build(),
                Category.builder().name("Baking").description("Breads and buns").build()
        );

        Product.builder().category(categories.get(0)).name("Apple").price(199).stock(68).build();
        Product.builder().category(categories.get(0)).name("Pear").price(299).stock(21).build();
        Product.builder().category(categories.get(1)).name("Carrot").price(199).stock(66).build();
        Product.builder().category(categories.get(1)).name("Tomato").price(249).stock(123).build();
        Product.builder().category(categories.get(1)).name("Potato").price(149).stock(72).build();
        Product.builder().category(categories.get(2)).name("Milk").price(499).stock(16).build();
        Product.builder().category(categories.get(2)).name("Yoghurt").price(299).stock(7).build();
        Product.builder().category(categories.get(3)).name("Bread").price(599).stock(23).build();
        Product.builder().category(categories.get(3)).name("Bun").price(99).stock(42).build();

        printCategories(categories);

        // Task 3
        System.out.println("\n3.");

        var products = categories.stream()
                .map(Category::getProducts)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        products.stream().forEach(System.out::println);

        // Task 4
        System.out.println("\n4.");

        products.stream()
                .filter(product -> product.getPrice() >= 200)
                .sorted(Comparator.comparingInt(Product::getStock))
                .forEach(System.out::println);

        // Task 5
        System.out.println("\n5.");

        var productDtos = products.stream()
                .map(ProductDto::new)
                .sorted()
                .toList();
        productDtos.stream().forEach(System.out::println);

        // Task 6
        System.out.println("\n6.");

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

        // Task 7
        System.out.println("\n7.");

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

    private static void printCategories(List<Category> categories) {
        categories.forEach(category -> {
            System.out.println("Category: " + category);
            category.getProducts().forEach(product -> {
                System.out.println("\tProduct: " + product);
            });
        });
    }
}