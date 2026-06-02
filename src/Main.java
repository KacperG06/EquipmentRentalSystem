import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Management management = new Management();
        Scanner scanner = new Scanner(System.in);
        //Interfejs
        do {
            System.out.println("Wybierz opcje: ");
            System.out.println("1.Dodaj klienta");
            System.out.println("2.Dodaj sprzęt ciężki");
            System.out.println("3.Dodaj sprzęt lekki");
            System.out.println("4.Wypożyczenie sprzętu");
            System.out.println("5.Zwrócenie sprzętu");
            System.out.println("0.Zamknij i zapisz program");
            int option = Integer.parseInt(scanner.nextLine());

                switch (option){
                    case 1: {
                        System.out.print("Podaj imie: ");
                        String name = scanner.nextLine();
                        System.out.print("Podaj nazwisko: ");
                        String surname = scanner.nextLine();
                        System.out.print("Podaj numer telefonu: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Podaj email: ");
                        String email = scanner.nextLine();
                        System.out.print("Podaj pesel (11 znakow): ");
                        String pesel = scanner.nextLine();
                        System.out.print("Podaj wiek: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        System.out.println("Czy posiadasz prawo jazdy (t/n)");
                        boolean driverLicense;
                        String pomDl;
                        do {
                            System.out.print("Czy potrzebne prawo jazdy (t/n): ");
                            pomDl = scanner.nextLine().toLowerCase();
                            if (pomDl.equals("t")) {
                                driverLicense = true;
                            } else if (pomDl.equals("n")) {
                                driverLicense = false;
                            } else {
                                System.out.print("Nieznana opcja. Podaj (t/n): ");
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
                        boolean driverLicenseEq;
                        String pomDlE;
                        do {
                            System.out.print("Czy potrzebne prawo jazdy (t/n): ");
                            pomDlE = scanner.nextLine().toLowerCase();
                            if (pomDlE.equals("t")) {
                                driverLicenseEq = true;
                            } else if (pomDlE.equals("n")) {
                                driverLicenseEq = false;
                            } else {
                                System.out.print("Nieznana opcja. Podaj (t/n): ");
                            }
                        } while (!pomDlE.equals("t") && !pomDlE.equals("n"));
                        System.out.print("Podaj numer rejestracyjny pojazdy (5 znakow): ");
                        String licensePlate = scanner.nextLine();
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
                        boolean transport;
                        String pomT;
                        do {
                            System.out.print("Czy potrzebne prawo jazdy (t/n): ");
                            pomT = scanner.nextLine().toLowerCase();
                            if (pomT.equals("t")) {
                                transport = true;
                            } else if (pomT.equals("n")) {
                                transport = false;
                            } else {
                                System.out.print("Nieznana opcja. Podaj (t/n): ");
                            }
                        } while (!pomT.equals("t") && !pomT.equals("n"));
                        management.addEquipment(new LightEquipment(id, equimpentName, price, transport));
                        break;
                    }
                    default:
                        System.out.println("Wybierz poprawna opcje! ");
                        break;

                }

            }while(option != 0);
        System.out.println("Pomyslnie zapisano i zakończono program");


    }
}