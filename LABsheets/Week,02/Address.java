public class Address {
    private String zipCode;
    private String country;
    private String city;

    public Address(String zipCode, String country, String city) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String get_zipCode(){
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

}