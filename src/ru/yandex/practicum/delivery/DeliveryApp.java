package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableItems = new ArrayList<>();

    static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(5000);
    static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<FragileParcel>(2000);
    static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<PerishableParcel>(10000);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            String choice = (scanner.nextLine());

            switch (choice) {
                case "1":
                    addParcel();
                    break;
                case "2":
                    sendParcels();
                    break;
                case "3":
                    calculateCosts();
                    break;
                case "4":
                    displayReportStatus();
                    break;
                case "5":
                    displayAllParcelsInBox();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Узнать статус хрупкой посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Введите содержимое посылки");
        String userInputDescription = scanner.nextLine();

        System.out.println("Введите вес посылки");
        int userInputWeight = 0;
        // try/catch, к слову, мы будем проходить только в середине седьмого спринта т.е. через 3 недели
        try {
            userInputWeight = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Введите вес цифрами");
            return;
        }

        System.out.println("Введите адрес отправки");
        String userInputDeliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки");
        int userInputSendDay = Integer.parseInt(scanner.nextLine());

        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Скоропортящаяся посылка");
        System.out.println("3 — Хрупкая посылка");

        String typeParcel = scanner.nextLine(); //Здесь

        switch (typeParcel) {
            case "1":
                StandardParcel standardParcel = new StandardParcel(userInputDescription, userInputWeight,
                        userInputDeliveryAddress, userInputSendDay);
                allParcels.add(standardParcel);
                standardParcelBox.addParcel(standardParcel);
                break;
            case "2":
                System.out.println("Введите срок годности посылки");
                int userInputTimeToLive = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishableParcel = new PerishableParcel(userInputDescription, userInputWeight,
                        userInputDeliveryAddress, userInputSendDay, userInputTimeToLive);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                break;
            case "3":
                FragileParcel fragileParcel = new FragileParcel(userInputDescription, userInputWeight,
                        userInputDeliveryAddress, userInputSendDay);
                allParcels.add(fragileParcel);
                trackableItems.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        int sumDeliveryCost = 0;
        if (allParcels != null && !allParcels.isEmpty()) {
            for (Parcel parcel : allParcels) {
                sumDeliveryCost += parcel.calculateDeliveryCost();
            }
        } else {
            System.out.println("У вас нет посылок");
        }
        System.out.println("Общая сумма доставки - " + sumDeliveryCost);
    }

    private static void displayReportStatus() {
        if (trackableItems != null && !trackableItems.isEmpty()) {
            for (Trackable trackable : trackableItems) {
                System.out.println("Введите новое месторасположения посылки");
                String userInputNewLocation = scanner.nextLine();
                trackable.reportStatus(userInputNewLocation);
            }
        } else {
            System.out.println("У вас нет отслеживаемых посылок");
        }
    }

    private static void displayAllParcelsInBox() {
        System.out.println("Выберите тип коробки:");
        System.out.println("1 — Стандартные посылки");
        System.out.println("2 — Скоропортящиеся посылки");
        System.out.println("3 — Хрупкие посылки");

        int typeBox = Integer.parseInt(scanner.nextLine());


        switch (typeBox) {
            case 1:
                if (standardParcelBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка пуста");
                } else {
                    for(StandardParcel standardParcel: standardParcelBox.getAllParcels()){
                        System.out.println(standardParcel.toString());
                    }
                }
                break;
            case 2:
                if (perishableParcelBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка пуста");
                } else {
                    for(PerishableParcel perishableParcel: perishableParcelBox.getAllParcels()){
                        System.out.println(perishableParcel.toString());
                    }
                }
                break;
            case 3:
                if (fragileParcelBox.getAllParcels().isEmpty()) {
                    System.out.println("Коробка пуста");
                } else {
                    for(FragileParcel fragileParcel: fragileParcelBox.getAllParcels()){
                        System.out.println(fragileParcel.toString());
                    }
                }
                break;
            default:
                System.out.println("Необрабатываемый ввод.");
        }
    }
}