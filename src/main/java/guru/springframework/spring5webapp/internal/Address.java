package guru.springframework.spring5webapp.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address{
    private String streetName;
    private String city;
    private String postalCode;


    @Override
    public String toString() {
        return "Address{" +
                "streetName=" + streetName +
                ", city=" + city +
                ", postalCode=" + postalCode +
                '}';
    }


    public String toThymeleafString() {
        return
                "streetName=" + streetName +
                ", city=" + city +
                ", postalCode=" + postalCode;
    }
    public static Address fromString(String addressString) {
        // Remove the "Address{" prefix and the "}" suffix
        String content = addressString.substring("Address{".length(), addressString.length() - 1);

        // Split by ", " and parse the fields
        String[] parts = content.split(", ");
        String streetName = parts[0].split("=")[1];
        String city = parts[1].split("=")[1];
        String postalCode = parts[2].split("=")[1];

        return new Address(streetName, city, postalCode);
    }
}