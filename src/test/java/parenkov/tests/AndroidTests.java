package parenkov.tests;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class AndroidTests extends TestBase {

    @Test
    @DisplayName("Passing onboarding screens")
    void onboardingTest() {
        step("Проверить корректность текста на первом экране", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("The Free Encyclopedia …in over 300 languages")));
        step("Кликнуть CONTINUE", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить корректность текста на втором экране", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("New ways to explore")));
        step("Кликнуть CONTINUE", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить корректность текста на третьем экране", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Reading lists with sync")));
        step("Кликнуть CONTINUE", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click());
        step("Проверить корректность текста на четвертом экране", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/primaryTextView"))
                        .shouldHave(text("Send anonymous data")));
        step("Кликнуть GET STARTED", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_done_button")).click());
    }

    @Test
    @DisplayName("Successful search")
    void searchTest() {
        step("Кликнуть SKIP на онбординг экране", () ->
                $(MobileBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click());
        step("Ввести поисковый запрос", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("Android");
        });
        step("Проверить, что найден контент", () ->
                $$(MobileBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }
}