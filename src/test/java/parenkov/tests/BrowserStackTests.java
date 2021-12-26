package parenkov.tests;

import io.appium.java_client.MobileBy;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import parenkov.config.CredentialsConfig;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Tag("browserstack")
public class BrowserStackTests extends TestBase {

    @Test
    @DisplayName("Successful search")
    void searchTest() {
        step("Ввести поисковый запрос", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Android");
        });
        step("Проверить, что найден контент", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_container"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Successful login")
    void logInTest() {

        credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

        step("Открыть Overflow Menu", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click());
        step("Перейти на страницу 'Log in to Wikipedia' кликнув по аватару", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_account_avatar")).click());
        step("Ввести username", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/login_username_text"))
                        .setValue(credentialsConfig.getUsername()));
        step("Ввести пароль", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/login_password_input")).click();
            $$(MobileBy.className("TextInputLayout")).get(1).
                    $(MobileBy.className("android.widget.EditText"))
                    .setValue(credentialsConfig.getPassword());
        });
        step("Кликнуть 'Log in'", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/login_button")).click());
        step("Проверить, что в Overflow Menu отображается username", () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/menu_overflow_button")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/explore_overflow_account_name"))
                    .shouldHave(text(credentialsConfig.getUsername()));
        });
    }
}