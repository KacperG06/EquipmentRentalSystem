import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentData implements DataManager<Equipment>{
    private String file;

    public EquipmentData(String file){
        this.file = file;
    }

    @Override
    public void save(List<Equipment> equipment) {
        System.out.println("Zapisano liste sprzętu: "+file);

    }

    @Override
    public List<Equipment> load() {
        List<Equipment> equipmentList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(";");
                int id = Integer.parseInt(data[0]);
                double price = Double.parseDouble(data[2]);
                boolean rentalStatus = Boolean.parseBoolean(data[3]);
                if (data.length > 5){
                    boolean driverLicense = Boolean.parseBoolean(data[4]);
                    HeavyEquipment newHeavy = new HeavyEquipment(id, data[1], price, rentalStatus, driverLicense, data[5]);
                    equipmentList.add(newHeavy);

                }
                else {
                    boolean transportRequired = Boolean.parseBoolean(data[4]);
                    LightEquipment newLight = new LightEquipment(id, data[1], price, rentalStatus, transportRequired);
                    equipmentList.add(newLight);
                }

            }
        }
        catch (FileNotFoundException e){
            System.out.println("Nie znaleziono pliku");
        }
        catch (IOException e){
            System.out.println("Coś poszło nie tak!");
        }
        return equipmentList;
    }
}
