package csulb.cecs323.Books;
import javax.persistence.*;
import java.util.Set;

@Entity
//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name="ReturnAllAdHocTeams",
//                query = "SELECT * " +
//                        "FROM   AdHocTeams "
//                       ,
//                resultClass = AdHocTeams.class
//        )
//})
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
