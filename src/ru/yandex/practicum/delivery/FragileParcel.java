package ru.yandex.practicum.delivery;
//«хрупкая посылка»
public class FragileParcel extends Parcel{
    
    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }
}
