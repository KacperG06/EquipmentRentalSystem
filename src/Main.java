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
            System.out.println("2.Dodaj sprzęt");
            System.out.println("3.Wypożyczenie sprzętu");
            System.out.println("4.Zwrócenie sprzętu");
            int option = scanner.nextInt();

            switch (option){
                case 1:
                    System.out.print("Podaj imie: ");
                    String name = scanner.nextLine();
                    System.out.print("Podaj nazwisko: ");
                    String surname = scanner.nextLine();
                    System.out.print("Podaj numer telefonu : ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Podaj email: ");
                    String email = scanner.nextLine();
                    System.out.print("Podaj pesel (11 znakow): ");
                    String pesel = scanner.nextLine();
                    System.out.print("Podaj wiek: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.println("Czy posiadasz prawo jazdy (t/n)");
                    String pomDl = scanner.nextLine().toLowerCase();
                    boolean driverLicense;
                    if (pomDl.equals("t")){
                        driverLicense = true;
                    }
                    else {
                        driverLicense = false;
                    }
                    management.addUser(new User(name, surname, phoneNumber, email,pesel, age, driverLicense));
                    break;
                case 2:

                default:
                    System.out.println("Wybierz poprawna opcje! ");
                    break;
            }

    }
}