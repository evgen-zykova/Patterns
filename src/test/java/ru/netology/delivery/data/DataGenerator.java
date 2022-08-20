package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Redistration {
        private Redistration() {
        }
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
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static UserInfo generateUser() {
        UserInfo user = new UserInfo(generateCity(), generateName(), generatePhone());
        return user;
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}

