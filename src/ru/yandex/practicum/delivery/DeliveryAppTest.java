package ru.yandex.practicum.delivery;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

public class DeliveryAppTest {

    @Test
    public void deliveryCostForStandardParcelShouldBe400() {
        StandardParcel standardParcelTest = new StandardParcel("Куртка", 200, "Томск, улица Лебедева, 39", 12);

        Assertions.assertEquals(400, standardParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для стандартной поссылки");
    }

    @Test
    public void deliveryCostForFragileParcelShouldBe80() {
        FragileParcel fragileParcelTest = new FragileParcel("Статуетка", 20, "Томск, улица Гоголя, 27", 12);

        Assertions.assertEquals(80, fragileParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для хрупкой поссылки");
    }

    @Test
    public void deliveryCostForPerishableParcelShouldBe3000() {
        PerishableParcel perishableParcelTest = new PerishableParcel("Бабушкины котлеты", 1000, "Кемерово, улица Веры Волошиной, 54", 12, 5);

        Assertions.assertEquals(3000, perishableParcelTest.calculateDeliveryCost(), "Неправильно посчитана сумма доставки для скоропортящейся поссылки");
    }

    @Test
    public void shouldBeGoBadAfterExpirationTimeToLive() {
        PerishableParcel perishableParcelTest =
                new PerishableParcel("Бабушкины котлеты", 1000, "Веры Волошиной, 54", 1, 5);

        Assertions.assertFalse(perishableParcelTest.isExpired(6));
    }

    @Test
    public void shouldBeGoNotSpoilBeforeExpirationTimeToLive() {
        PerishableParcel perishableParcelTest =
                new PerishableParcel("Бабушкины котлеты", 1000, "Веры Волошиной, 54", 1, 5);

        Assertions.assertFalse(perishableParcelTest.isExpired(4));
    }

    @Test
    public void shouldAddParcelWhenBoxIsEmpty() {
        StandardParcel standardParcelTest = new StandardParcel("Игрушка", 50, " улица Новая, 39", 12);
        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest);
        Assertions.assertTrue(standardParcelBoxTest.hasParcels);
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
    public void shouldNotAddParcelWhenBoxIsAlreadyFull() {
        StandardParcel standardParcelTest = new StandardParcel("Игрушка", 5001, " улица Новая, 39", 12);
        ParcelBox<StandardParcel> standardParcelBoxTest = new ParcelBox<>(5000);

        standardParcelBoxTest.addParcel(standardParcelTest);
        Assertions.assertFalse(standardParcelBoxTest.hasParcels);
    }
}
