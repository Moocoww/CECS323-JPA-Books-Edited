
package csulb.cecs323.Books;

// Import all of the entity classes that we have written for this application.
//import csulb.cecs323.model.*;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */

public class MainMenu {
    /**
     * You will likely need the entityManager in a great many functions throughout your application.
     * Rather than make this a global variable, we will make it an instance variable within the CarClub
     * class, and create an instance of CarClub in the main.
     */
    private EntityManager entityManager;

    /**
     * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
     * We use it because it is easy to control how much or how little logging gets done without having to
     * go through the application and comment out/uncomment code and run the risk of introducing a bug.
     * Here also, we want to make sure that the one Logger instance is readily available throughout the
     * application, without resorting to creating a global variable.
     */
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    /**
     * The constructor for the CarClub class.  All that it does is stash the provided EntityManager
     * for use later in the application.
     *
     * @param manager The EntityManager that we will use.
     */
    public MainMenu(EntityManager manager) {
        this.entityManager = manager;
    }


    public static void main(String[] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MainMenu");
        EntityManager manager = factory.createEntityManager();
        // Create an instance of CarClub and store our new EntityManager as an instance variable.
        MainMenu books = new MainMenu(manager);
        Scanner scnr = new Scanner(System.in);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();
        // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
//      List <Owners> owners = new ArrayList<Owners>();
//      owners.add(new Owners("Reese", "Mike", "714-892-5544"));
//      owners.add(new Owners("Leck", "Carl", "714-321-3729"));
//      owners.add(new Owners("Guitierez", "Luis", "562-982-2899"));
//      // Create the list of owners in the database.
//      carclub.createEntity (owners);
        //what happened to git
        //System.out.println("Enter a new publisher: ");
        //List<WritingGroups>

        //AuthoringEntities ae = new AuthoringEntities("janedoe@gmail.com", "Jane Doe", "Literature");  //cannot instantiate base class
//        ae = new IndividualAuthors();

        WritingGroups wg1 = new WritingGroups("shakespearefans@wg.com", "Shakespeare Fans", "Writing Group", "Shakespeare", 2022);
        WritingGroups wg2 = new WritingGroups("candyland@wg.com", "Candy Land", "Writing Group", "Candy", 2019);

        IndividualAuthors ia1 = new IndividualAuthors("georgeorwell@company.com", "George Orwell", "Individual Author");
        AdHocTeams adt1 = new AdHocTeams("companyname@company.com", "Pearsons Teams", "Ad Hoc Team");
        System.out.println(ia1);
        System.out.println(adt1);
        System.out.println(wg1);

        List<WritingGroups> wgs = new ArrayList<>();
        wgs.add(wg1);
        wgs.add(wg2);

        AdHocTeamMembers adtm1 = new AdHocTeamMembers(adt1, ia1);
        IndividualAuthors ia2 = new IndividualAuthors("mehrsabar@company.com", "Mehrsa Baradaran", "Individual Author");

        AdHocTeamMembers adtm2 = new AdHocTeamMembers(adt1, ia2);
        List<Books> booklist = new ArrayList<>();
        List<AdHocTeamMembers> totalmems = new ArrayList<AdHocTeamMembers>();
        totalmems.add(adtm1);
        totalmems.add(adtm2);
        System.out.println(totalmems);

//        System.out.println(adtm1);
//        System.out.println(adtm2);

        Publishers p1 = new Publishers("Oxford Publishers", "800-855-1234", "oxfordpublishers@oxford.com");
        Publishers p2 = new Publishers("Monkeys", "800-900-9999", "monkey@company.com");
        Books b1 = new Books("123456", "Animal Farm", p1, wg1, 1999);
        Books b2 = new Books("123450", "Animals Run", p1, ia1, 1990);
        System.out.println(p1);
        System.out.println(b1);


        Scanner scan = new Scanner(System.in);
        int menuOption = -1;
        boolean menuDone = false;
        boolean validMenuOption = false;
        List<Publishers> totalPublishers = new ArrayList<Publishers>();
        totalPublishers.add(p1);
        totalPublishers.add(p2);
        List<Books> totalBooks = new ArrayList<Books>();
        totalBooks.add(b1);
        totalBooks.add(b2);
        List<AuthoringEntities> totalAuthoringEntities = new ArrayList<AuthoringEntities>();

        while (!menuDone) {
            String menu = "-- Main Menu --" + "\nEnter an option: " + "\n1. Add an Authoring Entity"
                    + "\n2. Add Publisher" + "\n3. Add Book" + "\n4. List specific publisher information" +
                    "\n5. List specific book information" + "\n6. List specific writing group information" +
                    "\n7. Update a book" + "\n8. Delete a book" + "\n9. Quit";
            System.out.println(menu);
            while (!validMenuOption) {
                try {
                    menuOption = scnr.nextInt();
                    validMenuOption = true;

                } catch (Exception e) {
                    System.out.println("Re-enter a valid option.");
                    System.out.println(menu);
                    scnr.nextLine();
                }
            }

            switch (menuOption) {
                case 1:
                    //Authoring Entity
                    String aeType = "";
                    String aeName = "";
                    String aeEmail = "";
                    String headWriterName= "";
                    int yearFormed;
                    String addToTeam = "";

                    System.out.println("Enter an Authoring Entity Type (Writing Group, Individual Author, Ad Hoc Team): ");
                    aeType = scnr.nextLine();

                    if (aeType.equals("Individual Author")) {
                        System.out.println("Enter an Individual Author name: ");
                        aeName = scnr.nextLine();
                        System.out.println("Enter an Individual Author email: ");
                        aeEmail = scnr.nextLine();
                        IndividualAuthors ia = new IndividualAuthors(aeEmail, aeName, aeType);
                        totalAuthoringEntities.add(ia);

                        // iv. Add an Individual Author to an existing Ad Hoc Team
                        System.out.println("Would you like to add an Individual Author to an existing Ad Hoc Team? (y/n): ");
                        addToTeam = scnr.nextLine();
                        if (addToTeam.equalsIgnoreCase("y")) {
                           for (int i = 0; i < ) {

                           }

                           System.out.println("Enter Ad Hoc Team email: ");
                           String teamEmail = scnr.nextLine();
                        }

                    }
                    else if (aeType.equals("Ad Hoc Team")) {
                        System.out.println("Enter an Ad Hoc Team name: ");
                        aeName = scnr.nextLine();
                        System.out.println("Enter an Ad Hoc Team email: ");
                        aeEmail = scnr.nextLine();
                        AdHocTeams aht = new AdHocTeams(aeEmail, aeName, aeType);
                        totalAuthoringEntities.add(aht);

                    }else if(aeType.equals("Writing Group")){
                        System.out.println("Enter Writing Group name: ");
                        aeName = scnr.nextLine();
                        System.out.println("Enter Writing Group email: ");
                        aeEmail = scnr.nextLine();
                        System.out.println("Enter Head writer name: ");
                        headWriterName = scnr.nextLine();
                        System.out.println("Enter year formed: ");
                        yearFormed = scnr.nextInt();
                        WritingGroups wg = new WritingGroups(aeEmail, aeName, aeType, headWriterName, yearFormed);
                        wgs.add(wg);



                    }



                    System.out.println("Enter an Authoring Entity name: ");
                    scnr.nextLine();

                    //check for existing email
//                    boolean validAuthEntEmail = false;
//                    while (!validAuthEntEmail){
//                        System.out.println("Enter an Authoring Entity email: ");
//                        String authEntEmail = scnr.nextLine();
//
//
//
//                    }
                    //add writing group
                    System.out.println("Enter a Writing Group header writer name: ");
                    String writerName = scan.nextLine();
                    //check for existing writing group
//                    boolean exist = false;
//
////                    while (!exist){
////                        for (int i = 0; i < totalAuthoringEntities.size(); i++){
////                            if (totalAuthoringEntities.get(i).name)
////                        }
////                    }

                    //add individual author
                    //add ad hoc team
                    //add author to ad hoc team
                    validMenuOption = false;
                    scnr.nextLine();
                    break;
                case 2:
                    // Adding new Publisher
                    boolean validPubName = false;
                    String pubName = "";
                    System.out.println("Enter Publisher name:");
                    scnr.nextLine();
                    while (!validPubName) {
                        pubName = scnr.nextLine();

                        //checking if name already exists
                        boolean done = false;
                        while (!done) {
                            for (int i = 0; i < totalPublishers.size(); i++) {
                                if (totalPublishers.get(i).getName().equals(pubName)) {
                                    System.out.println("That Publisher already exists! Re-enter a new Publisher name.");
                                    validPubName = false;
                                    done = true;
                                }
                                else{
                                    done = true;
                                    validPubName = true;
                                }
                            }
                        }
                    }

                    System.out.println("Enter Publisher phone:");
                    String pubPhone = scnr.nextLine();

                    System.out.println("Enter Publisher email:");
                    String pubEmail = scnr.nextLine();

                    Publishers p = new Publishers(pubName, pubPhone, pubEmail);
                    totalPublishers.add(p);


                    validMenuOption = false;
                    //scnr.nextLine(); //clearing buffer
                    break;
                case 3:
                    // Adding new Book
                    boolean validBookISBN = false;
                    String ISBN = "";

                    System.out.println("Enter Book's ISBN:");
                    scnr.nextLine(); //clear buffer

                    while (!validBookISBN) {
                        ISBN = scnr.nextLine();

                        //checking if book already exists
                        boolean done = false;
                        while (!done) {
                            for (int i = 0; i < totalBooks.size(); i++) {
                                if (totalBooks.get(i).getISBN().equals(ISBN)) {
                                    System.out.println("That Book already exists! Re-enter a new Book ISBN.");
                                    validBookISBN= false;
                                    done = true;
                                }
                                else{
                                    done = true;
                                    validBookISBN = true;
                                }
                            }
                        }
                    }

                    System.out.println("Enter Book's title: ");
                    String bookTitle = scnr.nextLine();

                    System.out.println("Enter Book's publisher name: ");
                    String bookPub = scnr.nextLine();
                    Publishers bookPublisher = new Publishers();

                    boolean pubFound = false;
                    for (int i = 0; i < totalPublishers.size(); i++) {
                        // publisher already exists
                        if(totalPublishers.get(i).getName().equals(bookPub)){
                            bookPublisher = totalPublishers.get(i);
                            pubFound = true;
                        }
                        //publisher doesnt exist yet
                        else {
                            pubFound = false;
                        }
                    }
                    // publisher doesn't already exist, needs to be created first
                    if (!pubFound) {
                        System.out.println("Publisher doesn't already exist, publisher created!");

                        System.out.println("Enter Publisher phone:");
                        pubPhone = scnr.nextLine();

                        System.out.println("Enter Publisher email:");
                        pubEmail = scnr.nextLine();

                        bookPublisher = new Publishers(bookPub, pubPhone, pubEmail);
                        totalPublishers.add(bookPublisher);
                    }

                    System.out.println("Enter Book's authoring entity name: ");
                    String bookAuth = scnr.nextLine();
                    AuthoringEntities bookAuthEntity = new AuthoringEntities();

                    boolean aeFound = false;

                    for (int i = 0; i < totalAuthoringEntities.size(); i++) {
                        // Authoring Entity already exists
                        if (totalAuthoringEntities.get(i).getName().equals(bookAuth)) {
                            bookAuthEntity = totalAuthoringEntities.get(i);
                            aeFound = true;
                        }
                        // Authoring Entity doesn't exist yet
                        else {
                            aeFound = false;
                        }

                    }
                    // FIX ME: Authoring Entity doesn't already exist, needs to be created first
                    if (!aeFound) {
                        System.out.println("Authoring Entity doesn't already exist, creating Authoring Entity first");
                        // copy & paste code in case 1


                    }

                    System.out.println("Enter Book's year published: ");
                    int bookYear = getInt();


//                    Books b = new Books(ISBN, bookTitle, bookPublisher, bookAuthEntity, bookYear); // FIX ME
//                    totalBooks.add(b);

                    validMenuOption = false;
                    scnr.nextLine();
                    break;
                case 4:
                    //List specific publisher information
                    list_publisher_info(totalPublishers);
                    validMenuOption = false;

                    break;
                case 5:
                    // List specific book information
                    display_books(totalBooks);
                    validMenuOption = false;

                    break;
                case 6:
                   // List specific writing group information
                    list_writing_group_info(wgs);
                    validMenuOption = false;

                    break;
                case 7:
                    // Update a Book – Change the authoring entity for an existing book.
                    System.out.println("Enter the ISBN of the book you want to update: ");
                    String findISBN = scnr.nextLine();
                    MainMenu.update_book(totalBooks, findISBN);
                    break;
                case 8:
                    //delete a book
                    System.out.println("Enter the ISBN of the book you want to delete: ");
                    String deleteISBN = scnr.nextLine();
                    MainMenu.delete_book(totalBooks, deleteISBN);
                    break;
                case 9:
                    //quitting
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Re-enter a valid option.");
                    validMenuOption = false;
                    break;
            }
        }


        // Commit the changes so that the new data persists and is visible to other users.
        tx.commit();
        LOGGER.fine("End of Transaction");

    } // End of the main method

    /**
     * Create and persist a list of objects to the database.
     *
     * @param entities The list of entities to persist.  These can be any object that has been
     *                 properly annotated in JPA and marked as "persistable."  I specifically
     *                 used a Java generic so that I did not have to write this over and over.
     */
    public <E> void createEntity(List<E> entities) {
        for (E next : entities) {
            LOGGER.info("Persisting: " + next);
            // Use the CarClub entityManager instance variable to get our EntityManager.
            this.entityManager.persist(next);
        }

        // The auto generated ID (if present) is not passed in to the constructor since JPA will
        // generate a value.  So the previous for loop will not show a value for the ID.  But
        // now that the Entity has been persisted, JPA has generated the ID and filled that in.
        for (E next : entities) {
            LOGGER.info("Persisted object after flush (non-null id): " + next);
        }
    } // End of createEntity member method

    /**
     * Think of this as a simple map from a String to an instance of auto_body_styles that has the
     * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
     * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
     * representing the name of the style.
     * @param
     * @return The auto_body_styles instance corresponding to that style name.
     */
//   public auto_body_styles getStyle (String name) {
//      // Run the native query that we defined in the auto_body_styles entity to find the right style.
//      List<auto_body_styles> styles = this.entityManager.createNamedQuery("ReturnAutoBodyStyle",
//              auto_body_styles.class).setParameter(1, name).getResultList();
//      if (styles.size() == 0) {
//         // Invalid style name passed in.
//         return null;
//      } else {
//         // Return the style object that they asked for.
//         return styles.get(0);
//      }
//   }// End of the getStyle method


    /**
     *
     */
    public static void display_books(List <Books> books) {

        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available");
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;
            }
        }
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Book ISBN:");
        String bookName = "";
        boolean done = false;
        boolean found = false;
        while (!done) {
            bookName = scnr.nextLine();
            for (int i = 0; i < books.size(); i++) {

                if (books.get(i).getISBN().equals(bookName)) {
                    System.out.println(books.get(i).toString());
                    found = true;
                    done = true;
                }
            }
            if (!found) {
                System.out.println("Invalid ISBN. Please Re-enter ISBN: ");
            }
        }

    }//end of display_books
//    /**
//     *
//     */
//    public static void display_books(List <Books> books) {
//
//        System.out.println("-----Books Information----");
//        if (books.size() == 0){
//            System.out.println("No books available");
//        }
//        else{
//            for (int i = 0; i < books.size(); i++){
//                System.out.println(books.get(i).getISBN()) ;
//            }
//        }
//        Scanner scnr = new Scanner(System.in);
//        System.out.println("Enter Book ISBN:");
//        String bookName = "";
//        boolean done = false;
//        while (!done) {
//            bookName = scnr.nextLine();
//            for (int i = 0; i < books.size(); i++) {
//                boolean found = false;
//                if (books.get(i).getISBN().equals(bookName)) {
//                    System.out.println(books.get(i).toString());
//                    found = true;
//                    done = true;
//                }
//
//                if (!found) {
//                    System.out.println("Invalid ISBN. Please Re-enter ISBN: ");
//                }
//            }
//        } //end of display_book

//    /**
//     * @param booklist
//     * @param ISBN
//     */
    public static void delete_book(List<Books> booklist, String ISBN) {
        for (int i = 0; i < booklist.size(); i++) {
            if (booklist.get(i).getISBN().equals(ISBN)) {
                booklist.remove(i);
                System.out.println("Book successfully deleted.");
            }
        }
    }

    public static void update_book(List<Books> booklist, String ISBN) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter the Authoring Entity Type (Writing Group, Individual Author, Ad Hoc Team): ");
        String updatedType = scnr.nextLine();

        if (updatedType.equals("Individual Author")) {
            for (int i = 0; i < booklist.size(); i++) {
                if (booklist.get(i).getISBN().equals(ISBN)) {
                    // create new object updatedEntity of type updatedType
                    //booklist.get(i).get auth ent name
                    //booklist.get(i).get auth ent email
                    //booklist.get(i).setAuthoring_entity_name(updatedEntity);
                }
            }
        }
        else if (updatedType.equals("Ad Hoc Team")) {
            for (int i = 0; i < booklist.size(); i++) {
                if (booklist.get(i).getISBN().equals(ISBN)) {
                    booklist.get(i).getAuthoring_entity_name().setAuthoring_entity_type(updatedType);
                }
            }
        }else if(updatedType.equals("Writing Group")){
            System.out.println("");

        }

    }

    /**
     * @return
     */
    public static int getInt() {
        Scanner in = new Scanner(System.in);
        int input = 0;
        boolean valid = false;
        while (!valid) {
            if (in.hasNextInt()) {
                input = in.nextInt();
                valid = true;
            } else {
                in.next(); //clear invalid string
                System.out.println("Invalid Input.");
            }
        }
        return input;
    }

    public static void list_publisher_info(List<Publishers> publishers) {
        if (publishers.size() == 0) {
            System.out.println("No Publishers available.");
        }
        else {
            System.out.println("-----Publisher Information----");
            for (int i = 0; i < publishers.size(); i++) {
                System.out.println((i+1) + ". \t" + publishers.get(i).getName());
            }
        }

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a Publisher Name:");
        String pubName = "";
        boolean validInput = false;

        while (!validInput) {
            pubName = scnr.nextLine();
            boolean found = false;
            for (int i = 0; i < publishers.size(); i++) {
                if (publishers.get(i).getName().equals(pubName)) {
                    System.out.println(publishers.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }
            if (!found) {
                System.out.println("Publisher not found. Re-enter Publisher name: ");
            }
        }
    }
    public static void list_writing_group_info(List<WritingGroups> wg) {
        if (wg.size() == 0) {
            System.out.println("No Writing Group available.");
        }
        else {
            System.out.println("-----Writing Group Information----");
            for (int i = 0; i < wg.size(); i++) {
                System.out.println((i+1) + ". \t" + wg.get(i).getName());
            }
        }

        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter a Writing Group Name:");
        String pubName = "";
        boolean validInput = false;

        while (!validInput) {
            pubName = scnr.nextLine();
            boolean found = false;
            for (int i = 0; i < wg.size(); i++) {
                if (wg.get(i).getName().equals(pubName)) {
                    System.out.println(wg.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }
            if (!found) {
                System.out.println("Writing Group not found. Re-enter Writing Group name: ");
            }
        }
    }
}
