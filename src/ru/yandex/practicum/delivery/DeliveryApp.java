package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<FragileParcel> trackableItems = new ArrayList<>();


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
                    calculateCosts(
                            
                    );
                    break;
                case "4":
                    displayReportStatus();
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
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Введите содержимое поссылки");
        String userInputDescription = scanner.nextLine();

        System.out.println("Введите вес поссылки");
        int userInputWight = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес отправки");
        String userInputDeliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки");
        int userInputSendDay = Integer.parseInt(scanner.nextLine());

        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Скоропортящаяся посылка");
        System.out.println("3 — Хрупкая посылка");
        int typeParcel = Integer.parseInt(scanner.nextLine());

        switch (typeParcel) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(userInputDescription, userInputWight,
                        userInputDeliveryAddress, userInputSendDay);
                allParcels.add(standardParcel);
                break;
            case 2:
                System.out.println("Введите срок годности посылки");
                int userInputtTmeToLive = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishableParcel = new PerishableParcel(userInputDescription, userInputWight,
                        userInputDeliveryAddress, userInputSendDay, userInputtTmeToLive);
                allParcels.add(perishableParcel);
                break;
            case 3:
                FragileParcel fragileParcel = new FragileParcel(userInputDescription, userInputWight,
                        userInputDeliveryAddress, userInputSendDay);
                allParcels.add(fragileParcel);
                trackableItems.add(fragileParcel);

                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран

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
        if (allParcels != null && !allParcels.isEmpty()) {
            for (Parcel parcel : allParcels) {
                if (parcel instanceof FragileParcel) {
                    for (FragileParcel fragileParcel : trackableItems) {
                        System.out.println("Введите новое месторасположения посылки");
                        String userInputNewLocation = scanner.nextLine();
                        fragileParcel.reportStatus(userInputNewLocation);
                    }
                } else {
                    System.out.println("У вас нет посылки, которая поддерживает трекинг");
                }
            }
        } else {
            System.out.println("У вас нет отправленных посылок");
        }
    }
}


