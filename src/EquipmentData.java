import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentData implements DataManager<Equipment>{
    private String file;

    public EquipmentData(String file){
        this.file = file;
    }

    @Override
    public void save(List<Equipment> equipment) {
        try (FileWriter fileWriter = new FileWriter(new File(file))){
            for (Equipment eq : equipment){
                String line = eq.getId() + ";" + eq.getName() + ";" + eq.getPrice() + ";" + eq.getRentalStatus();
                if (eq instanceof HeavyEquipment){
                    String heavyeq = line + ";" + ((HeavyEquipment) eq).hasDriverLicense() + ";" + ((HeavyEquipment) eq).getLicensePlate() + "\n";
                    fileWriter.write(heavyeq);
                } else if (eq instanceof  LightEquipment) {
                    String lighteq = line + ";" + ((LightEquipment) eq).isTransportRequired() + "\n";
                    fileWriter.write(lighteq);
                }

            }
        }
        catch (IOException e ){
            throw new RuntimeException("Bład zapisu pliku");
        }


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
                    HeavyEquipment newHeavy = new HeavyEquipment(id, data[1], price, driverLicense, data[5]);
                    if (rentalStatus){
                        newHeavy.setRented();
                    }
                    equipmentList.add(newHeavy);

                }
                else {
                    boolean transportRequired = Boolean.parseBoolean(data[4]);
                    LightEquipment newLight = new LightEquipment(id, data[1], price, transportRequired);
                    if (rentalStatus) {
                        newLight.setRented();
                    }
                    equipmentList.add(newLight);
                }

            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku, utworzono nowy");;
        }
        catch (IOException e){
            throw new RuntimeException("Bład odczytu pliku");        }
        return equipmentList;
    }
}
