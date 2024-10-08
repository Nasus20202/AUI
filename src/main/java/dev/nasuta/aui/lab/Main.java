package dev.nasuta.aui.lab;

import dev.nasuta.aui.lab.entity.*;
import dev.nasuta.aui.lab.dto.*;
import dev.nasuta.aui.lab.entity.Character;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class  Main {
    final static String fileName = "characters.dat";
    final static int threadCount = Runtime.getRuntime().availableProcessors();
    final static int threadTimeout = 1000;

    public static void main(String[] args) {
        System.out.println("2.");

        var professions = List.of(
                Profession.builder().name("Warrior").baseArmor(100).build(),
                Profession.builder().name("Archer").baseArmor(75).build(),
                Profession.builder().name("Mage").baseArmor(25).build(),
                Profession.builder().name("Rogue").baseArmor(50).build()
        );

        Character.builder().profession(professions.get(0)).name("Mark").level(10).build();
        Character.builder().profession(professions.get(0)).name("Jones").level(22).build();
        Character.builder().profession(professions.get(0)).name("Smith").level(35).build();
        Character.builder().profession(professions.get(1)).name("Alice").level(15).build();
        Character.builder().profession(professions.get(1)).name("Bob").level(30).build();
        Character.builder().profession(professions.get(1)).name("Cathy").level(45).build();
        Character.builder().profession(professions.get(2)).name("Charlie").level(5).build();
        Character.builder().profession(professions.get(2)).name("David").level(27).build();
        Character.builder().profession(professions.get(3)).name("Eve").level(20).build();
        Character.builder().profession(professions.get(3)).name("Frank").level(40).build();

        printProfessions(professions);

        System.out.println("\n3.");

        var characters = professions.stream()
                .map(Profession::getCharacters)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        characters.stream().forEach(System.out::println);

        System.out.println("\n4.");

        characters.stream()
                .filter(character -> character.getProfession().getBaseArmor() > 50)
                .sorted(Comparator.comparingInt(Character::getLevel))
                .forEach(System.out::println);

        System.out.println("\n5.");

        var characterDtos = characters.stream()
                .map(CharacterDto::new)
                .sorted()
                .toList();
        characterDtos.stream().forEach(System.out::println);

        System.out.println("\n6.");

        try {
            var fileOutputStream = new FileOutputStream(fileName);
            var objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(professions);
            objectOutputStream.flush();
            objectOutputStream.close();

            var fileInputStream = new FileInputStream(fileName);
            var objectInputStream = new ObjectInputStream(fileInputStream);
            var serializedCategories = (List<Profession>) objectInputStream.readObject();
            objectInputStream.close();

            printProfessions(serializedCategories);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n7.");

        var threadPool = new ForkJoinPool(threadCount);
        Runnable job = () -> professions.parallelStream().forEach(profession ->
            profession.getCharacters().forEach(character -> {
                var threadName = Thread.currentThread().getName();

                try {
                    Thread.sleep(threadTimeout);
                } catch (InterruptedException e) {
                    System.out.println(threadName + " | " + e.getMessage());
                }

                System.out.println(threadName + " | " + profession.getName() + " - " + character.getName());
            }));

        threadPool.submit(job);
        threadPool.close();
    }

    private static void printProfessions(List<Profession> professions) {
        professions.forEach(profession -> {
            System.out.println("Profession: " + profession);
            profession.getCharacters().forEach(character -> {
                System.out.println("\tCharacter: " + character);
            });
        });
    }
}