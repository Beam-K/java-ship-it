package ru.yandex.practicum.delivery;

//«скоропортящаяся посылка»
public class PerishableParcel extends Parcel {
    private static final int PERISHABLE_PRICE_PER_KG = 3;

    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    public boolean isExpired(int currentDay) {

        if((sendDay + timeToLive) >= currentDay){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int calculateDeliveryCost() {
        return PERISHABLE_PRICE_PER_KG * weight;
    }
}
