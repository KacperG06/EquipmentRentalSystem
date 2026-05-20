import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalData implements DataManager<Rental>{
    private String file;
    private List<User> userList;
    private List<Equipment> equipmentList;

    public RentalData(String file, List<User> userList, List<Equipment> equipmentList){
        this.file = file;
        this.userList = userList;
        this.equipmentList = equipmentList;
    }

    @Override
    public void save(List<Rental> rentals) {
        System.out.println("Zapisano wypozyczenia: "+file);

    }

    @Override
    public List<Rental> load() {
        List<Rental> rentalList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine())!= null){
                String[] data = line.split(";");
                User foundUser = null;
                Equipment foundEquipment = null;

                for (User user : userList){
                    if (user.getPesel().equals(data[1])){
                        foundUser = user;
                        break;
                    }
                }

                for (Equipment equipment : equipmentList){
                    if (equipment.getId() == Integer.parseInt(data[2])){
                        foundEquipment = equipment;
                        break;
                    }
                }

                int id = Integer.parseInt(data[0]);
                LocalDateTime start = LocalDateTime.parse(data[3]);
                LocalDateTime end = LocalDateTime.parse(data[4]);
                double totalCost = Double.parseDouble(data[5]);


                Rental newRental = new Rental(id, foundUser, foundEquipment, start, end, totalCost);
                rentalList.add(newRental);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        }
        catch (IOException e){
            System.out.println("Coś poszło nie tak!");
        }
        return rentalList;

    }
}
