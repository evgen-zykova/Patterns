package ru.netology.delivery.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Redistration {
        private Redistration() {
        }
    }

    static {
        new Faker(new Locale("ru"));
    }

    public static User generateUser() {
        return new User(generateCity(), generateDate(6), generateName(), generatePhone());
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Москва", "Пенза", "Волгоград", "Саратов", "Казань"};
        int itemIndex = (int) (Math.random() * cities.length);
        return cities[itemIndex];
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        AtomicReference<String> randomName = new AtomicReference<>(faker.name().firstName() + " " + faker.name().lastName());
        return randomName.get();
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }
}

