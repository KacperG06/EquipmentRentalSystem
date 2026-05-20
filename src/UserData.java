import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserData implements DataManager<User> {
    private String file;

    public UserData(String file){
        this.file = file;
    }

    @Override
    public void save(List<User> users) {
        System.out.println("Zapisano dane uzytkownikow: "+file);

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
            System.out.println("Nie znaleziono pliku!");
        }
        catch (IOException e){
            System.out.println("Coś poszło nie tak!");
        }

        return userList;
    }
}
