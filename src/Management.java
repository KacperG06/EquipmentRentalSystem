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

    public Management(String usersFile, String equipmentFile, String rentalsFile){
        this.userData = new UserData(usersFile);
        this.equipmentData = new EquipmentData(equipmentFile);

        this.userList = userData.load();
        this.equipmentList = equipmentData.load();

        this.rentalData = new RentalData(rentalsFile, this.userList, this.equipmentList);

        List<Rental> allRentals = rentalData.load();
        this.rentalData = new RentalData(rentalsFile,userList,equipmentList);
        this.rentalHistory = new ArrayList<>();
        this.activeRentals = new ArrayList<>();

        for (Rental rental : allRentals){
            if (!rental.getEquipment().isAvailable()){
                this.activeRentals.add(rental);
            }
            else {
                this.rentalHistory.add(rental);
            }
            if (rental.getId() > lastRentalIdNumber){
                lastRentalIdNumber = rental.getId();
            }
        }
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
        System.out.printf("Kwota do zapłaty: %.2f zł\n", newRental.getTotalCost());
    }

    public void returnEquipment(Rental rental){
        rentalHistory.add(rental);
        activeRentals.remove(rental);
    }

    private double calculateTotalCost(Equipment equipment, LocalDateTime startDate, LocalDateTime endDate){
        int numOfDays = Period.between(startDate.toLocalDate(), endDate.toLocalDate()).getDays();
        if (numOfDays <= 0) { //zero dni liczmy jako 1 dzien
            numOfDays = 1;
        }
        double pricePerDayType = 0.0;
        if (equipment instanceof LightEquipment) {
            pricePerDayType = 0.01;
        } else if (equipment instanceof HeavyEquipment) {
            pricePerDayType = 0.008;
        }
        return equipment.getPrice() * pricePerDayType * numOfDays;
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

    public void saveAllData(){
        userData.save(userList);
        equipmentData.save(equipmentList);

        List<Rental> allRentalsToSave = new ArrayList<>();

        allRentalsToSave.addAll(rentalHistory);
        allRentalsToSave.addAll(activeRentals);

        rentalData.save(allRentalsToSave);
    }
    
}
