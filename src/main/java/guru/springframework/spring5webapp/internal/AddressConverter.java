package guru.springframework.spring5webapp.internal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AddressConverter implements AttributeConverter<Address, String> {

    @Override
    public String convertToDatabaseColumn(Address address) {
        return address == null ? null : address.toString();
    }

    @Override
    public Address convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Address.fromString(dbData);
    }
}