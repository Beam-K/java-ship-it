package ru.yandex.practicum.delivery;

public class StandardParcel extends Parcel{

    private static final int STANDARD_PRICE_PER_KG = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int getBaseCost(){
        return STANDARD_PRICE_PER_KG;
    }
}
