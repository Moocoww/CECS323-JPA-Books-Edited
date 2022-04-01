package csulb.cecs323.Books;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class WritingGroups extends AuthoringEntities {

    @Id
    @Column (nullable = false, length = 30)
    private String head_writer;
    @Id
    @Column(nullable = false, length = 4)
    private int year_formed;

    public WritingGroups(String email, String name, String ae, String head_writer, int year_formed) {
        super(email, name, ae);
        this.head_writer = head_writer;
        this.year_formed = year_formed;
    }

    public WritingGroups() {
        super();
    }

    public String getHead_writer() {
        return head_writer;
    }

    public void setHead_writer(String head_writer) {
        this.head_writer = head_writer;
    }

    public int getYear_formed() {
        return year_formed;
    }

    public void setYear_formed(int year_formed) {
        this.year_formed = year_formed;
    }

    @Override
    public String toString() {
        return "Email: " + getEmail() + "\t| Name: "+ getName() + "\t| Authoring Entity Type: " +getAuthoring_entity_type()+ "\t| Head writer: " + head_writer + "\t| Year formed: " + year_formed;
    }
    @Override
    public int hashCode(){return Objects.hash(this.getHead_writer(), this.getYear_formed());}
}
