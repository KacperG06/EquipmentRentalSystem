public class User {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String pesel;
    private int age;
    private boolean driverLicense;


    public User(String name, String surname, String phoneNumber, String email,String pesel, int age, boolean driverLicense){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pesel = pesel;
        this.age = age;
        this.driverLicense = driverLicense;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPesel() {
        return pesel;
    }

    public int getAge() {
        return age;
    }

    public boolean hasDriverLicense() {
        return driverLicense;
    }
}
