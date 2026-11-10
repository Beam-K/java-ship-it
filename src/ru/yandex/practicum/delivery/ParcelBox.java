package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> ParcelBoxes = new ArrayList<>();
    int maxWeightBox;
    int currentWeightBox = 0;
    boolean hasParcels = false;

    public ParcelBox(int maxWeightBox) {
        this.maxWeightBox = maxWeightBox;
    }
    public void addParcel(T parcel) {
        if (currentWeightBox + parcel.weight > maxWeightBox) {
            System.out.println("Коробка переполнена");
        }else {
            ParcelBoxes.add(parcel);
            currentWeightBox += parcel.weight;
            hasParcels = true;
        }
    }

    public void getAllParcels(){
        for (Parcel parcel: ParcelBoxes){
            System.out.println(" Содержимое коробки - " + parcel.description);
            System.out.println(parcel);
        }
    }
    public int getParcelsCount() {
        return ParcelBoxes.size();
    }
}
