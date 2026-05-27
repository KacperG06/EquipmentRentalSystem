import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserData implements DataManager<User> {
    private String file;

    public UserData(String file){
        this.file = file;
    }

    @Override
    public void save(List<User> users) {
        try(FileWriter fileWriter = new FileWriter(new File(file))){
            for (User user : users){
                fileWriter.write(user.getName() +";" + user.getSurname() + ";" + user.getPhoneNumber() + ";" +user.getEmail() + ";" + user.getPesel() + ";" + user.getAge() + ";" + user.hasDriverLicense()+"\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Bład zapisu pliku");
        }

    }

    @Override
    public List<User> load() {
        List<User> userList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                String[] data = line.split(";");
                int age = Integer.parseInt(data[5]);
                boolean driverLicense = Boolean.parseBoolean(data[6]);
                User newUser = new User(data[0], data[1], data[2], data[3], data[4], age, driverLicense);
                userList.add(newUser);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Nie znaleziono plik");
        }
        catch (IOException e){
            throw new RuntimeException("Bład odczytu pliku");
        }

        return userList;
    }
}
