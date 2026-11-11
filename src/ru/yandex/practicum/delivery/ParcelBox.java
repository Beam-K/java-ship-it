package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> parcels = new ArrayList<>();
    int maxWeightBox;
    int currentWeightBox = 0;

    public ParcelBox(int maxWeightBox) {
        this.maxWeightBox = maxWeightBox;
    }

    public void addParcel(T parcel) {
        if (currentWeightBox + parcel.weight > maxWeightBox) {
            System.out.println("Коробка переполнена");
        } else {
            parcels.add(parcel);
            currentWeightBox += parcel.weight;
        }
    }

    public ArrayList<T> getAllParcels() {
        return parcels;
    }



    public int getParcelsCount() {
        return parcels.size();
    }
}
