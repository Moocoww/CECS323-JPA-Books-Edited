package csulb.cecs323.Books;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(
                name="ReturnAllBooks",
                query = "SELECT * " +
                        "FROM   BOOKS ",
                resultClass = Books.class
        ),
        @NamedNativeQuery(
                name="ReturnABook",
                query = "SELECT * " +
                        "FROM   BOOKS " +
                        "WHERE  ISBN = ?",
                resultClass = Books.class
        ),
        @NamedNativeQuery(
                name="ReturnBooksPrimaryKey",
                query = "SELECT ISBN " +
                        "FROM   BOOKS ",
                resultClass = Books.class
        )
})
public class Books {
    @Id
    @Column(nullable=false, length = 17)
    private String ISBN;

    @Column(nullable=false,length = 80)
    private String title;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private Publishers publisher_name;

    @ManyToOne
    @JoinColumn(name="email", referencedColumnName = "email")
    private AuthoringEntities authoring_entity_name;

    @Column(nullable=false, length = 4)
    private int year_published;

    public Books() {
    }

    public Books(String ISBN, String title, Publishers publisher_name, AuthoringEntities authoring_entity_name, int year_published) {
        this.ISBN =ISBN;
        this.title = title;
        this.publisher_name = publisher_name;
        this.authoring_entity_name = authoring_entity_name;
        this.year_published = year_published;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publishers getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(Publishers publisher_name) {
        this.publisher_name = publisher_name;
    }

    public AuthoringEntities getAuthoring_entity_name() {
        return authoring_entity_name;
    }

    public void setAuthoring_entity_name(AuthoringEntities authoring_entity_name) {
        this.authoring_entity_name = authoring_entity_name;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    @Override
    public String toString(){
        return "ISBN: " + this.ISBN + "\t| Title: " + this.title + "\t| Publisher name: " + this.publisher_name.getName() + "\t| Authoring Entity Name: " + this.authoring_entity_name.getName() + "\t| Year Published: " + this.year_published;
    }
    @Override
    public int hashCode(){return Objects.hash(this.getISBN() , this.getTitle() , this.getPublisher_name() , this.getAuthoring_entity_name() , this.getYear_published());}


} // End of Books class
