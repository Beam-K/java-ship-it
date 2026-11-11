package ru.yandex.practicum;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

public class DeliveryCostTest {

    @Test
    public void deliveryCostForStandardParcelShouldBe400() {
        StandardParcel standardParcelTest = new StandardParcel("Куртка", 200, "Томск, улица Лебедева, 39", 12);

        Assertions.assertEquals(400, standardParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для стандартной посылки");
    }

    @Test
    public void deliveryCostForFragileParcelShouldBe80() {
        FragileParcel fragileParcelTest = new FragileParcel("Статуэтка", 20, "Томск, улица Гоголя, 27", 12);

        Assertions.assertEquals(80, fragileParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для хрупкой посылки");
    }

    @Test
    public void deliveryCostForPerishableParcelShouldBe3000() {
        PerishableParcel perishableParcelTest = new PerishableParcel("Бабушкины котлеты", 1000, "Кемерово, улица Веры Волошиной, 54", 12, 5);

        Assertions.assertEquals(3000, perishableParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для скоропортящейся посылки");
    }
    @Test
    public void shouldNotExpireBeforeTimeToLive() {
        PerishableParcel perishableParcelTest =
                new PerishableParcel("Бабушкины котлеты", 1000, "Веры Волошиной, 54", 1, 5);

        Assertions.assertFalse(perishableParcelTest.isExpired(4));
    }
    @Test
    public void shouldNotExpireExactlyOnTimeToLive() {
        PerishableParcel perishableParcelTest =
                new PerishableParcel("Бабушкины котлеты", 1000, "Веры Волошиной, 54", 1, 5);

        Assertions.assertFalse(perishableParcelTest.isExpired(5));
    }

    @Test
    public void shouldExpireAfterTimeToLive() {
        PerishableParcel perishableParcelTest =
                new PerishableParcel("Бабушкины котлеты", 1000, "Веры Волошиной, 54", 1, 5);

        Assertions.assertTrue(perishableParcelTest.isExpired(6));
    }

    @Test
    public void shouldAddParcelWhenBoxIsEmpty() {
        StandardParcel standardParcelTest = new StandardParcel("Игрушка", 50, " улица Новая, 39", 12);
        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest);
        Assertions.assertEquals(1, standardParcelBoxTest.getParcelsCount());
    }

    @Test
    public void shouldAddParcelAtExactMaxWeight() {
        StandardParcel standardParcelTest1 = new StandardParcel("Игрушка", 50, " улица Новая, 39", 12);
        StandardParcel standardParcelTest2 = new StandardParcel("Игрушка", 4950, " улица Новая, 39", 12);
        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest1);
        standardParcelBoxTest.addParcel(standardParcelTest2);
        Assertions.assertEquals(2, standardParcelBoxTest.getParcelsCount());
    }

    @Test
    public void shouldNotAddParcelWhenWeightExceedsLimitByOne() {
        StandardParcel standardParcelTest1 = new StandardParcel("Игрушка", 50, " улица Новая, 39", 12);
        StandardParcel standardParcelTest2 = new StandardParcel("Игрушка", 4951, " улица Новая, 39", 12);

        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest1);
        standardParcelBoxTest.addParcel(standardParcelTest2);
        Assertions.assertEquals(1, standardParcelBoxTest.getParcelsCount());
    }

    @Test
    public void shouldNotAddParcelWhenSingleParcelExceedsMaxWeight() {
        StandardParcel standardParcelTest = new StandardParcel("Игрушка", 5001, " улица Новая, 39", 12);
        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest);
        Assertions.assertEquals(0, standardParcelBoxTest.getParcelsCount());
    }
}
