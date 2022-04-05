package csulb.cecs323.Books;
import javax.persistence.*;
import java.util.Set;

@Entity
public class AdHocTeams extends AuthoringEntities{

    @ManyToMany
    Set<IndividualAuthors> author;

    public AdHocTeams() {
        super();
    }

    public AdHocTeams(String email, String name, String ae) {
        super(email, name, ae);


    }



}
