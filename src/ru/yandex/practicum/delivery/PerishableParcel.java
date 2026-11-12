package ru.yandex.practicum.delivery;

//«скоропортящаяся посылка»
public class PerishableParcel extends Parcel {
    private static final int PERISHABLE_PRICE_PER_KG = 3;

    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {

        return currentDay > sendDay + timeToLive; //
    }

    @Override
    public int getBaseCost() {
        return PERISHABLE_PRICE_PER_KG;
    }

    @Override
    public String toString() {
        return "Посылка: " + description + ", вес: " + weight + "г, адрес: " + deliveryAddress + " дата отправки: " + sendDay + " срок годности: " + timeToLive;
    }
}
