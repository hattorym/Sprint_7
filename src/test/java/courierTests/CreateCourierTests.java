package courierTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.courier.*;

public class CreateCourierTests {
    protected final CourierRandomizer courierRandomizer = new CourierRandomizer();
    int courierId;
    private CourierSteps courierSteps;
    private CourierModel courierModel;
    private CourierAssert courierAssert;

    @Before
    @Step("Создание тестовых данных курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        courierModel = courierRandomizer.createNewRandomCourier();
        courierAssert = new CourierAssert();
    }

    @After
    @Step("Удаление тестовых данных")
    public void deleteCourier() {
        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверяем, что курьера можно создать")
    public void courierCanBeCreated() {
        ValidatableResponse responseCreateCourier = courierSteps.createCourier(courierModel);
        CourierCreds courierCreds = CourierCreds.from(courierModel);
        courierId = courierSteps.loginCourier(courierCreds).extract().path("id");
        courierAssert.createCourierOk(responseCreateCourier);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем логина")
    @Description("Проверяем, что курьера нельзя создать без логина")
    public void courierCanNotBeCreatedWithoutLogin() {
        courierModel.setLogin(null);
        ValidatableResponse responseNullLogin = courierSteps.createCourier(courierModel);
        courierAssert.createCourierError(responseNullLogin);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем пароля")
    @Description("Проверяем, что курьера нельзя создать без пароля")
    public void courierCanNotBeCreatedWithoutPassword() {
        courierModel.setPassword(null);
        ValidatableResponse responseNullPassword = courierSteps.createCourier(courierModel);
        courierAssert.createCourierError(responseNullPassword);
    }

    @Test
    @DisplayName("Создание курьера с пустым полем логина и пароля")
    @Description("Проверяем, что курьера нельзя создать без ввода логина и  пароля")
    public void courierCanNotBeCreatedWithoutLoginAndPassword() {
        courierModel.setLogin(null);
        courierModel.setPassword(null);
        ValidatableResponse responseNullFields = courierSteps.createCourier(courierModel);
        courierAssert.createCourierError(responseNullFields);
    }

    @Test
    @DisplayName("Создание курьера с ранее зарегистрированным логином")
    @Description("Проверяем, что курьера нельзя создать с ранее зарегистрированным логином")
    public void courierCanNotBeCreatedWithSameLogin() {
        courierSteps.createCourier(courierModel);
        ValidatableResponse responseCreateCourier = courierSteps.createCourier(courierModel);
        courierAssert.createCourierSameLoginError(responseCreateCourier);
    }

}