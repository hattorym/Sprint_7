package ru.yandex.courier;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class CourierRandomizer {
    static Faker faker = new Faker();

//    public static final String LOGIN_FAKED = faker.name().username();
//    public static final String PASSWORD_FAKED = faker.internet().password();
//    public static final String FIRST_NAME_FAKED = faker.name().firstName();
    public static final String NEW_LOGIN_FAKED = faker.name().username();

//    public static final String CHUCK = faker.chuckNorris().fact();
//    public void main(String[] args) {
//        System.out.println(LOGIN_FAKED);
//        System.out.println(PASSWORD_FAKED);
//        System.out.println(FIRST_NAME_FAKED);
//        System.out.println(CHUCK); //минутка Чака
//    }

    @Step("Создание нового курьера с рандомными данными")
    public CourierModel createNewRandomCourier() {
        return new CourierModel(
                faker.name().username(),
                faker.internet().password(),
                faker.name().firstName());

//                return new CourierModel();
//                        LOGIN_FAKED,
//                        PASSWORD_FAKED,
//                        FIRST_NAME_FAKED);
    }
}