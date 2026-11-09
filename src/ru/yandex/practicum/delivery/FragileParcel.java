package ru.yandex.practicum.delivery;

//«хрупкая посылка»
public class FragileParcel extends Parcel implements Trackable {

    private static final int FRAGILE_PRICE_PER_KG = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return FRAGILE_PRICE_PER_KG * weight;
    }
    @Override
    public void packageItem(){
        System.out.println("Посылка "+ description + " обёрнута в защитную плёнку");
        System.out.println("Посылка "+ description + " упакована");
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка "+ description + " изменила местоположение на " + newLocation);


    }
}
