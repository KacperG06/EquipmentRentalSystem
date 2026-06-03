import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj nazwe pliku z uzytkownikami: ");
        String usersFile = scanner.nextLine();
        System.out.print("Podaj nazwe pliku z lista sprzetu: ");
        String equimentFile = scanner.nextLine();
        System.out.print("Podaj nazwe pliku z wypozyczeniami: ");
        String rentalsFile = scanner.nextLine();
        Management management = new Management(usersFile, equimentFile, rentalsFile);

        int option = -1;
        do {
            System.out.println("1.Dodaj klienta");
            System.out.println("2.Dodaj sprzęt ciężki");
            System.out.println("3.Dodaj sprzęt lekki");
            System.out.println("4.Wypożyczenie sprzętu");
            System.out.println("5.Zwrócenie sprzętu");
            System.out.println("6.Wyswietl wszystkich uzytkownikow");
            System.out.println("7.Wyswietl caly asortyment wypozyczalni");
            System.out.println("8.wyswietl sprzet dla danego uzytkowka");
            System.out.println("9.wyswietl uzytkownikow dla danego sprzetu");
            System.out.println("0.Zamknij i zapisz program");
            System.out.print("Wybierz opcje: ");
            option = readIntSafely(scanner, "");

                switch (option) {
                    case 1: {
                        String name = readSafely(scanner, "Podaj imię: ");
                        String surname = readSafely(scanner, "Podaj nazwisko: ");
                        String phoneNumber = "";
                        do {
                            phoneNumber = readSafely(scanner, "Podaj numer telefonu 9 cyfr: ");
                            if (!checkPhoneNmber(phoneNumber)){
                                System.out.println("Numer telefonu musi skaldac sie z 9 cyfr");
                            }
                        } while (!checkPhoneNmber(phoneNumber));
                        String email = readSafely(scanner, "Podaj emial");
                        String pesel = "";
                        boolean peselTaken = false;
                        do {
                            System.out.print("Podaj pesel 11 cyfr: ");
                            pesel = scanner.nextLine();

                            peselTaken = isPeselTaken(pesel, management);
                            if (!checkPesel(pesel)) {
                                System.out.println("Pesel musi zawierac 11 cyfr");
                            } else if(peselTaken){
                                System.out.println("Uyztkownik o takim numerze pesel juz istnieje");
                            }
                        } while (!checkPesel(pesel) || peselTaken);
                        int age = readIntSafely(scanner, "Podaj wiek: ");
                        boolean driverLicense = false;
                        String pomDl;
                        do {
                            System.out.println("Czy posiadasz prawo jazdy (t/n)");
                            pomDl = scanner.nextLine().toLowerCase();
                            if (!pomDl.equals("t") && !pomDl.equals("n")) {
                                System.out.println("Wpisz t albo n!");
                            } else {
                                driverLicense = setBoolean(pomDl);
                            }

                        } while (!pomDl.equals("t") && !pomDl.equals("n"));
                        management.addUser(new User(name, surname, phoneNumber, email, pesel, age, driverLicense));
                        break;
                    }
                    case 2: {
                        int id = readIntSafely(scanner, "Podaj id sprzetu: ");
                        String equimpentName = readSafely(scanner, "Podaj nazwę sprzętu: ");
                        double price = readDoubleSafely(scanner, "Podaj cenę sprzętu: ");
                        boolean driverLicenseEq = false;
                        String pomDlE;
                        do {
                            System.out.print("Czy potrzebne prawo jazdy (t/n): ");
                            pomDlE = scanner.nextLine().toLowerCase();
                            if (!pomDlE.equals("t") && !pomDlE.equals("n")) {
                                System.out.println("Wpisz t albo n!");
                            } else {
                                driverLicenseEq = setBoolean(pomDlE);
                            }
                        } while (!pomDlE.equals("t") && !pomDlE.equals("n"));

                        String licensePlate = "";
                        do {
                            licensePlate = readSafely(scanner, "Podaj numer rejestracyjny pojazdy (5 znakow))");
                            if (!checkLicensePlate(licensePlate)) {
                                System.out.println("Tablica rejestracyjna musi miec 5 znaków!");
                            }
                        } while (!checkLicensePlate(licensePlate));
                        management.addEquipment(new HeavyEquipment(id, equimpentName, price, driverLicenseEq, licensePlate));
                        break;
                    }
                    case 3: {
                        int id = readIntSafely(scanner, "Podaj id sprzętu: ");
                        String equimpentName =readSafely(scanner, "Podaj nazwę sprzetu: ");
                        double price = readDoubleSafely(scanner, "Podaj cene sprzetu: ");
                        boolean transport = false;
                        String pomT;
                        do {
                            System.out.print("Czy wymagany transport (t/n): ");
                            pomT = scanner.nextLine().toLowerCase();
                            if (!pomT.equals("t") && !pomT.equals("n")) {
                                System.out.println("Wpisz t albo n!");
                            } else {
                                transport = setBoolean(pomT);
                            }

                        } while (!pomT.equals("t") && !pomT.equals("n"));
                        management.addEquipment(new LightEquipment(id, equimpentName, price, transport));
                        break;
                    }
                    case 4: {
                        String peselR = "";
                        do {
                            peselR = readSafely(scanner, "Podaj pesel: ");
                            if (!checkPesel(peselR)) {
                                System.out.println("Pesel musi zawierac 11 cyfr");
                            }
                        } while (!checkPesel(peselR));
                        int equipmentIdR = readIntSafely(scanner, "Podaj id sprzetu do wypozycenia: ");
                        User userFound = null;
                        Equipment founfEquipment = null;

                        for (User user : management.getUserList()) {
                            if (peselR.equals(user.getPesel())) {
                                userFound = user;
                                break;
                            }
                        }

                        for (Equipment equipment : management.getEquipmentList()) {
                            if (equipmentIdR == equipment.getId()) {
                                founfEquipment = equipment;
                                break;
                            }
                        }
                        int days = readIntSafely(scanner, "Na ile dni chcesz wypozyczyc?: ");

                        LocalDateTime endDate = LocalDateTime.now().plusDays(days);
                        try {
                            management.rentEquipment(userFound, founfEquipment, endDate);

                        } catch (IllegalArgumentException e) {
                            System.out.println("Nie znaleziono uzytkownika lub sprzętu");
                        } catch (EquipmentUnavailableExcpetion e) {
                            System.out.println("Sprzet jest niedostępny!");
                        } catch (NoDrivingLicenseException e) {
                            System.out.println("Brak Prawa jazdy");
                        } catch (IllegalDateException e) {
                            System.out.println("Nieprawidlowa koncowa data");
                        }

                        break;
                    }
                    case 5: {
                        int equipmentIdToReturn = readIntSafely(scanner, "Podaj id sprzętu, który chcesz zwrócić: ");
                        Rental rentalPom = null;
                        for (Rental rental : management.getActiveRentals()) {
                            if (rental.getEquipment().getId() == equipmentIdToReturn) {
                                rentalPom = rental;
                                break;
                            }
                        }
                        if (rentalPom != null) { //sprzęt znaleziono
                            management.returnEquipment(rentalPom);
                            rentalPom.getEquipment().setAvailable();
                            System.out.println("Pomyślnie zwrócono sprzęt: " + rentalPom.getEquipment().getName());
                        } else {
                            System.out.println("Nie ma takiego wyporzyczenia!");
                        }
                        break;
                    }
                    case 6:{
                        System.out.println("Lista wszystkich klientow:");
                        for (User user : management.getUserList()){
                            System.out.println(user);
                        }
                        break;
                    }
                    case 7:{
                        System.out.println("Lista Sprzetu w wypozyczalni:");
                        for (Equipment equipment : management.getEquipmentList()){
                            System.out.println(equipment);
                        }
                        break;
                    }
                    case 8:{
                        String peselSearch = "";
                        boolean foundUser= false;
                        do {
                            peselSearch = readSafely(scanner, "Podaj pesel aby sprawdzic wypozyczenia (11 znakow): ");
                            if (!checkPesel(peselSearch)) {
                                System.out.println("Pesel musi zawierac 11 cyfr");
                            }
                        } while (!checkPesel(peselSearch));
                        System.out.println("Sprzet wypozyczony przez: " + peselSearch);
                        for (Rental rental : management.getActiveRentals()){
                            if (rental.getUser().getPesel().equals(peselSearch)){
                                System.out.println("- "+rental.getEquipment().getName() + " id: " + rental.getEquipment().getId());
                                foundUser = true;
                            }
                        }
                        System.out.println("Historia wypozyczen! ");
                        for (Rental rental : management.getRentalHistory()){
                            if (rental.getUser().getPesel().equals(peselSearch)){
                                System.out.println("- " + rental.getEquipment().getName() + " id: " + rental.getEquipment().getId());
                                foundUser = true;
                            }
                        }
                        if (!foundUser){
                            System.out.println("Ten klient nie ma wypozyczonego sprzetu");
                        }
                        break;
                    }
                    case 9:{
                        int equipmentSearchId = readIntSafely(scanner, "Podaj id sprzetu ktorego szukasz: ");
                        boolean foundEquipment = false;

                        for (Rental rental : management.getActiveRentals()){
                            if (rental.getEquipment().getId() == equipmentSearchId){
                                System.out.println("Sprzęt wypozyczony przez : " + rental.getUser().getName() + " " + rental.getUser().getSurname() + " Telefon: " + rental.getUser().getPhoneNumber());
                                foundEquipment = true;
                                break;
                            }
                        }
                        if (!foundEquipment){
                            System.out.println("Ten sprzet nie jest obecnie wypozyczony");
                        }
                        break;
                    }
                    default:
                        System.out.println("Wybierz poprawna opcje! ");
                        break;

                }

            }while(option != 0);
        management.saveAllData();
        System.out.println("Pomyslnie zapisano i zakończono program");


    }

    private static boolean checkPesel(String pesel){
        if (pesel.matches("\\d{11}")){
            return true;
        }
        else return false;

    }

    private static boolean checkPhoneNmber(String phoneNumber){
        if (phoneNumber.matches("\\d{9}")){
            return true;
        }
        else return false;

    }

    private static boolean checkLicensePlate(String licencePlate){
        if (licencePlate.length() == 5){
            return true;
        }
        else return false;

    }

    private static boolean setBoolean(String pom){
        if (pom.equals("t")){
            return true;
        } else {
            return false;
        }
    }

    private static String readSafely(Scanner scanner, String question){
        String input;
        do {
            System.out.println(question);
            input = scanner.nextLine().trim();
            if (input.isEmpty()){
                System.out.println("Pole nie moze byc puste");
            }
        }while (input.isEmpty());
        return input;
    }

    private static int readIntSafely(Scanner scanner, String question) {
        while (true) {
            System.out.print(question);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wpisz liczbę całkowitą!");
            }
        }
    }

    private static double readDoubleSafely(Scanner scanner, String question) {
        while (true) {
            System.out.print(question);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Wpisz liczbę poprawna kwote!");
            }
        }
    }

    private static boolean isPeselTaken(String pesel, Management management){
        for (User user : management.getUserList()){
            if (user.getPesel().equals(pesel)){
                return true;
            }
        }
        return false;
    }


}