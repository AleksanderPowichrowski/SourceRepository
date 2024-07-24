package guru.springframework.spring5webapp.domain;

import guru.springframework.spring5webapp.internal.Address;
import guru.springframework.spring5webapp.internal.AddressConverter;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class PublisherV2 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Convert(converter = AddressConverter.class)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private final Set<Book> releasedBooks = new HashSet<>();

    public void setAddress(String streetName, String city, String postalCode) {
        this.address = new Address(streetName, city, postalCode);
    }


    @Override
    public String toString() {
        return "PublisherV2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address.toString() +
                ", books=" + booksToString(releasedBooks) +
                " }";
    }

    private String booksToString(Set<Book> releasedBooks) {
        StringBuilder sb = new StringBuilder();
        for (Book book : releasedBooks) {
            sb.append("Book{id=").append(book.getId()).append(", title='").append(book.getTitle()).append("'}");
        }
        return sb.toString();
    }

   public String booksToThymeleafString(){
        StringBuilder sb = new StringBuilder();
        for(Book book : releasedBooks){
            sb.append("id:=").append(book.getId()).append(", title=").append(book.getTitle());
        }
        return sb.toString();
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublisherV2 that = (PublisherV2) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }
}
