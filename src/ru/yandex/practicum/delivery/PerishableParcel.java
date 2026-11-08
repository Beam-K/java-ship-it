package ru.yandex.practicum.delivery;
//«скоропортящаяся посылка»
public class PerishableParcel extends Parcel{
    int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay,int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }
}
