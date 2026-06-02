import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Management {
    private List<User> userList;
    private List<Equipment> equipmentList;
    private List<Rental> activeRentals;
    private List<Rental> rentalHistory;

    private UserData userData;
    private EquipmentData equipmentData;
    private RentalData rentalData;
    int lastRentalIdNumber = 0;
    public Management(){
        this.userData = new UserData("users.txt");
        this.equipmentData = new EquipmentData("equipment.txt");

        this.userList = userData.load();
        this.equipmentList = equipmentData.load();

        this.rentalData = new RentalData("rentals.txt",userList,equipmentList);
        this.rentalHistory = rentalData.load();
    }

    public void addUser(User user){
        userList.add(user);
        System.out.println("Pomyślnie dodano użytkownika: " + user.getName() + user.getSurname());
    }

    public void addEquipment(Equipment equipment){
        equipmentList.add(equipment);
        System.out.println("Pomyślnie dodano sprzęt: " + equipment.getName());
    }


    public void rentEquipment(User user, Equipment equipment, LocalDateTime endDate)throws IllegalArgumentException, EquipmentUnavailableExcpetion, NoDrivingLicenseException, IllegalDateException{
        if((user == null) || (equipment == null) || (endDate == null)){
            throw new IllegalArgumentException();
        }
        if(!equipment.isAvailable()){
            throw new EquipmentUnavailableExcpetion();
        }
        if(!equipment.canBeRentedBy(user)){
            throw new NoDrivingLicenseException();
        }
        if(endDate.isBefore(LocalDateTime.now())){
            throw new IllegalDateException();
        }
        Rental newRental = new Rental(++lastRentalIdNumber, user, equipment, LocalDateTime.now(), endDate, calculateTotalCost(equipment, LocalDateTime.now(), endDate));
        equipment.setRented();
        activeRentals.add(newRental);
        System.out.println("Pomyślnie wypożyczono: " + equipment.getName() + " dla użytkownika: " + user.getName());
    }

    public void returnEquipment(Rental rental){
        if(rental.getEndDate().isBefore(LocalDateTime.now())){
            rentalHistory.add(rental);
            activeRentals.remove(rental);
        }
    }

    private double calculateTotalCost(Equipment equipment, LocalDateTime startDate, LocalDateTime endDate){
        int numOfDays = Period.between(startDate.toLocalDate(), endDate.toLocalDate()).getDays();
        return equipment.getPrice() * 0.05 * numOfDays;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public List<Rental> getActiveRentals() {
        return activeRentals;
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }
}
