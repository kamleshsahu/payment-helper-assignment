package dev.kamlesh.paymenthelper.testutility;

import com.github.javafaker.Faker;

public final class FakerUtility {
    private static final Faker faker = new Faker();

    private FakerUtility() {
    }

    public static String getRandomString() {
        return faker.lorem().characters();
    }

    public static long getRandomNumber() {
        return faker.number().randomNumber();
    }
}
