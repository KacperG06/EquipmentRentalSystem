import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Management management = new Management();
        Scanner scanner = new Scanner(System.in);
        //Interfejs
        int option = -1;
        do {
            System.out.println("Wybierz opcje: ");
            System.out.println("1.Dodaj klienta");
            System.out.println("2.Dodaj sprzęt ciężki");
            System.out.println("3.Dodaj sprzęt lekki");
            System.out.println("4.Wypożyczenie sprzętu");
            System.out.println("5.Zwrócenie sprzętu");
            System.out.println("0.Zamknij i zapisz program");
            option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1: {
                        System.out.print("Podaj imie: ");
                        String name = scanner.nextLine();
                        System.out.print("Podaj nazwisko: ");
                        String surname = scanner.nextLine();
                        System.out.print("Podaj numer telefonu: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Podaj email: ");
                        String email = scanner.nextLine();
                        String pesel = "";
                        do {
                            System.out.print("Podaj pesel (11 znakow): ");
                            pesel = scanner.nextLine();
                            if (!checkPesel(pesel)) {
                                System.out.println("Pesel musi zawierac 11 znaków");
                            }
                        } while (!checkPesel(pesel));

                        System.out.print("Podaj wiek: ");
                        int age = Integer.parseInt(scanner.nextLine());
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
                        System.out.print("Podaj id sprzętu: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Podaj nazwę sprzętu: ");
                        String equimpentName = scanner.nextLine();
                        System.out.print("Podaj cenę sprzętu: ");
                        double price = Double.parseDouble(scanner.nextLine());
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
                            System.out.print("Podaj numer rejestracyjny pojazdy (5 znakow): ");
                            licensePlate = scanner.nextLine();
                            if (!checkLicensePlate(licensePlate)) {
                                System.out.println("Tablica rejestracyjna musi miec 5 znaków!");
                            }
                        } while (!checkLicensePlate(licensePlate));
                        management.addEquipment(new HeavyEquipment(id, equimpentName, price, driverLicenseEq, licensePlate));
                        break;
                    }
                    case 3: {
                        System.out.print("Podaj id sprzętu: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Podaj nazwę sprzętu: ");
                        String equimpentName = scanner.nextLine();
                        System.out.print("Podaj cenę sprzętu: ");
                        double price = Double.parseDouble(scanner.nextLine());
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
                            System.out.print("Podaj pesel (11 znakow): ");
                            peselR = scanner.nextLine();
                            if (!checkPesel(peselR)) {
                                System.out.println("Pesel musi zawierac 11 znaków");
                            }
                        } while (!checkPesel(peselR));

                        System.out.print("Podaj id sprzetu do wypozycenia: ");
                        int equipmentIdR = Integer.parseInt(scanner.nextLine());
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

                        System.out.print("Na ile dni chcesz wypozyczyc? ");
                        int days = Integer.parseInt(scanner.nextLine());

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
                        System.out.print("Podaj id sprzętu, który chcesz zwrócić: ");
                        int equipmentIdToReturn = Integer.parseInt(scanner.nextLine());
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
                    default:
                        System.out.println("Wybierz poprawna opcje! ");
                        break;

                }

            }while(option != 0);
        System.out.println("Pomyslnie zapisano i zakończono program");


    }

    private static boolean checkPesel(String pesel){
        if (pesel.length() == 11){
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
}