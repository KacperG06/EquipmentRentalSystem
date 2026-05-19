import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public Management(){
        this.userData = new UserData("users.txt");
        this.equipmentData = new EquipmentData("equipment.txt");
        this.rentalData = new RentalData("rentals.txt");

        this.userList = userData.load();
        this.equipmentList = equipmentData.load();
        this.rentalHistory = rentalData.load();
    }

    public void addUser(User user){

    }

    public void addEquipment(Equipment equipment){

    }


    public void rentEquipment(User user, Equipment equipment, LocalDateTime endDate){

    }

    public void returnEquipment(Rental rental){

    }

    private double calculateTotalCost(Equipment equipment, LocalDateTime startDate, LocalDateTime endDate){

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
