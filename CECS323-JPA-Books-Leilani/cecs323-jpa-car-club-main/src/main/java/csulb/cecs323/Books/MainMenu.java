package csulb.cecs323.Books;
// Import all of the entity classes that we have written for this application.
//import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.awt.print.Book;
import java.util.*;
import java.util.logging.Logger;

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

        // Create an instance of books and store our new EntityManager as an instance variable.
        MainMenu books = new MainMenu(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        tx.begin();

        //writing group objects
        WritingGroups wg1 = new WritingGroups("shakespearefans@wg.com", "Shakespeare Fans", "Writing Group", "Shakespeare", 2022);
        WritingGroups wg2 = new WritingGroups("candyland@wg.com", "Candy Land", "Writing Group", "Candy", 2019);

        //individual author objects
        IndividualAuthors ia1 = new IndividualAuthors("georgeorwell@company.com", "George Orwell", "Individual Author");
        IndividualAuthors ia2 = new IndividualAuthors("mehrsabar@company.com", "Mehrsa Baradaran", "Individual Author");

        //add hoc teams objects
        AdHocTeams adt1 = new AdHocTeams("companyname@company.com", "Pearsons Teams", "Ad Hoc Team");
        AdHocTeams adt2 = new AdHocTeams("anotherco@company.com", "Some Other Team", "Ad Hoc Team");

        //ad hoc team members objects
        AdHocTeamMembers adtm1 = new AdHocTeamMembers(adt1, ia1);
        AdHocTeamMembers adtm2 = new AdHocTeamMembers(adt1, ia2);

        //publisher objects
        Publishers p1 = new Publishers("Oxford Publishers", "800-855-1234", "oxfordpublishers@oxford.com");
        Publishers p2 = new Publishers("Monkeys", "800-900-9999", "monkey@company.com");

        //book objects
        Books b1 = new Books("123456", "Animal Farm", p1, wg1, 1999);
        Books b2 = new Books("123450", "Animals Run", p1, ia1, 1990);

        Scanner scnr = new Scanner(System.in);
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
        List<IndividualAuthors> totalIndividualAuthors = new ArrayList<IndividualAuthors>();
        totalIndividualAuthors.add(ia1);
        totalIndividualAuthors.add(ia2);
        List<AdHocTeams> totalAdHocTeams = new ArrayList<AdHocTeams>();
        totalAdHocTeams.add(adt1);
        totalAdHocTeams.add(adt2);
        List<WritingGroups> totalWritingGroups = new ArrayList<WritingGroups>();
        totalWritingGroups.add(wg1);
        totalWritingGroups.add(wg2);
        List<AdHocTeamMembers> totalMembers = new ArrayList<AdHocTeamMembers>();
        totalMembers.add(adtm1);
        totalMembers.add(adtm2);

        //add all the author groups into Authoring Entities
        totalAuthoringEntities.add(ia1); //add individual author
        totalAuthoringEntities.add(ia2); //add individual author

        totalAuthoringEntities.add(wg1); //add writing group
        totalAuthoringEntities.add(wg2); //add writing group

        totalAuthoringEntities.add(adt1); //add adhocteam
        totalAuthoringEntities.add(adt2); //add adhocteam

        //create entity for database
        books.createEntity(totalPublishers); // create the list of publishers in the database
        books.createEntity(totalBooks);
        books.createEntity(totalAuthoringEntities);
        books.createEntity(totalIndividualAuthors);
        books.createEntity(totalAdHocTeams);
        books.createEntity(totalWritingGroups);
        books.createEntity(totalMembers);

        //user menu starts here
        while (!menuDone) {
            String menu = "-- Main Menu --" + "\nEnter an option: " + "\n1. Add an Authoring Entity"
                    + "\n2. Add Publisher" + "\n3. Add Book" + "\n4. List specific publisher information" +
                    "\n5. List specific book information" + "\n6. List specific writing group information" +
                    "\n7. Update a book" + "\n8. Delete a book" + "\n9. List primary key information"+ "\n10. Quit";
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
                    AuthoringEntities someAuthEntity = addAuthoringEntity(totalIndividualAuthors, totalAdHocTeams, totalWritingGroups, totalMembers, totalAuthoringEntities);
                    validMenuOption = false;
                    break;
                case 2:
                    // Adding new Publisher
                    String pubName = checkPublisherName(totalPublishers);
                    System.out.println("pubName: " + pubName);
                    scnr.nextLine();
                    System.out.println("Enter Publisher phone:");
                    String pubPhone = scnr.nextLine();

                    System.out.println("Enter Publisher email:");
                    String pubEmail = scnr.nextLine();

                    Publishers p = new Publishers(pubName, pubPhone, pubEmail);
                    totalPublishers.add(p);

                    validMenuOption = false;
                    break;

                case 3:
                    // Adding new Book
                    Publishers bookPublisher = new Publishers();
                    String ISBN = checkISBN(totalBooks);
                    scnr.nextLine();
                    System.out.println("Enter Book's title: ");
                    String bookTitle = scnr.nextLine();

                    //check for existing book publisher name
                    System.out.println("Enter Publisher name: ");
                    //String publisherName = checkPublisherName(totalPublishers);
                    String publisherName = scnr.nextLine();
                    boolean publisherExists = false;
                    pubPhone = "";
                    pubEmail = "";
                    int index = -1;

                    //if publisher already exists, autofill the publisher phone and email
                    for (int i = 0; i < totalPublishers.size(); i++) {
                        if (totalPublishers.get(i).getName().equalsIgnoreCase(publisherName)) {
                            pubPhone = totalPublishers.get(i).getPhone();
                            pubEmail = totalPublishers.get(i).getEmail();
                            index = i;
                            publisherExists = true;
                        }
                    }
                    //if publisher DOESN'T already exist, ask user to enter phone and email
                    if (publisherExists == false) {
                        System.out.println("Enter Publisher phone:");
                        pubPhone = scnr.nextLine();
                        System.out.println("Enter Publisher email:");
                        pubEmail = scnr.nextLine();
                    }

                    bookPublisher = new Publishers(publisherName, pubPhone, pubEmail);
                    totalPublishers.add(bookPublisher);
                    System.out.println("-- Enter Book's Authoring Entity Information -- ");
                    //create a book's authoring entity
                    AuthoringEntities bookAuthEntity = addAuthoringEntity(totalIndividualAuthors, totalAdHocTeams, totalWritingGroups, totalMembers,totalAuthoringEntities);
                    System.out.println("Enter Book's year published: ");
                    int bookYear = getInt();

                    if (publisherExists == true) {
                        Books b = new Books(ISBN, bookTitle, totalPublishers.get(index), bookAuthEntity, bookYear);
                        totalBooks.add(b);
                    }else {
                        Books b = new Books(ISBN, bookTitle, bookPublisher, bookAuthEntity, bookYear);
                        totalBooks.add(b);

                    }
                    //Books b = new Books(ISBN, bookTitle, bookPublisher, bookAuthEntity, bookYear);
                    //totalBooks.add(b);

                    validMenuOption = false;
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
                    list_writing_group_info(totalWritingGroups);
                    validMenuOption = false;
                    break;
                case 7:
                    // Update a Book – Change the authoring entity for an existing book.
                    MainMenu.update_book(totalBooks, totalIndividualAuthors, totalAdHocTeams, totalWritingGroups, totalMembers, totalAuthoringEntities);
                    validMenuOption = false;
                    break;
                case 8:
                    //delete a book
                    MainMenu.delete_book(totalBooks);
                    validMenuOption = false;
                    break;
                case 9:
                    //List primary key information for publishers, books, and Authoring Entities
                    String menuselect = ("Select a choice below: "+
                            "\n1.\tList primary key for Publishers."+
                            "\n2.\tList primary key for Books."+
                            "\n3.\tList primary key for Authoring Entities."

                    );
                    System.out.println(menuselect);
                    boolean valid = false;
                    while(!valid) {
                        int pk = scnr.nextInt();
                        if (pk == 1) {
                            getPublisherPK(totalPublishers);
                            valid = true;

                        }
                        else if (pk == 2) {
                            getBooksPK(totalBooks);
                            valid = true;
                        }
                        else if (pk == 3) {

                            getAuthoringEntityPK(totalAuthoringEntities);
                            valid = true;
                        }
                        else {
                            System.out.println("Invalid input. " + menuselect);
                            scnr.nextLine();
                            valid = false;

                        }
                    }
                    validMenuOption = false;
                    break;
                case 10:
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
     * Function to add authoring entity
     * @param totalIA
     * @param totalAHT
     * @param totalWG
     * @param totalMems
     * @param totalAE
     * @return an object of authoring entity
     */
    public static AuthoringEntities addAuthoringEntity(List<IndividualAuthors> totalIA, List<AdHocTeams> totalAHT, List<WritingGroups> totalWG, List<AdHocTeamMembers> totalMems, List<AuthoringEntities> totalAE) {
        Scanner scnr = new Scanner(System.in);
        String aeType = "";
        String aeName = "";
        String aeEmail = "";
        String headWriterName= "";
        int yearFormed;
        String addToTeam = "";

        AuthoringEntities someAuthEntity = new AuthoringEntities();
        System.out.println("Enter an Authoring Entity Type (Writing Group, Individual Author or Ad Hoc Team): ");
        //check for valid authoring entity type
        boolean valid = false;
        while (!valid) {
            aeType = scnr.nextLine();
            if (aeType.equalsIgnoreCase("Individual Author")) {
                System.out.println("Enter an Individual Author name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter an Individual Author email: ");
                aeEmail = scnr.nextLine();

                //check for new valid author email
                IndividualAuthors someAuthor = new IndividualAuthors();
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someAuthor.setEmail(aeEmail);
                    someAuthor.setName(aeName);
                    someAuthor.setAuthoring_entity_type(aeType);
                    totalAE.add(someAuthor);
                    totalIA.add(someAuthor);
                    someAuthEntity = someAuthor;
                }
                // iv. Add an Individual Author to an existing Ad Hoc Team
                boolean doneAdding = false;
                while (!doneAdding) {
                    System.out.println("Would you like to add an Individual Author to an existing Ad Hoc Team? (y/n): ");
                    addToTeam = scnr.nextLine();
                    if (addToTeam.equalsIgnoreCase("y")) {
                        AdHocTeamMembers newMember = new AdHocTeamMembers();

                        //display all existing ad hoc teams
                        MainMenu.showAllAdHocTeam(totalAHT);
                        String teamEmail = "";
                        checkAdHocEmail(newMember,totalAHT);
                        newMember.setIndividualAuthor(someAuthor);
                        totalMems.add(newMember); //adding to junction table list
                        doneAdding = true;
                    }
                    else if (addToTeam.equalsIgnoreCase("n")){
                        doneAdding = true;
                    }
                    else{
                        System.out.println("Invalid option. Please enter y/n. ");//whatever
                    }
                }
                valid = true; //
            }
            else if (aeType.equalsIgnoreCase("Ad Hoc Team")) {
                System.out.println("Enter an Ad Hoc Team name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter an Ad Hoc Team email: ");
                aeEmail = scnr.nextLine();

                AdHocTeams someTeam = new AdHocTeams();
                //check if email already exists
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someTeam.setEmail(aeEmail);
                    someTeam.setName(aeName);
                    someTeam.setAuthoring_entity_type(aeType);
                    totalAE.add(someTeam);
                    totalAHT.add(someTeam);
                    someAuthEntity = someTeam;
                }
                valid = true;

            } else if (aeType.equalsIgnoreCase("Writing Group")) {
                System.out.println("Enter Writing Group name: ");
                aeName = scnr.nextLine();
                System.out.println("Enter Writing Group email: ");
                aeEmail = scnr.nextLine();
                //check if email already exists
                WritingGroups someGroup = new WritingGroups();
                if (getValidAuthorEmail(aeEmail, totalAE) == true) {
                    System.out.println("Email is good");
                    someGroup.setEmail(aeEmail);
                    someGroup.setName(aeName);
                    someGroup.setAuthoring_entity_type(aeType);
                }
                System.out.println("Enter Head writer name: ");
                headWriterName = scnr.nextLine();
                System.out.println("Enter year formed: ");
                yearFormed = scnr.nextInt();
                someGroup.setHead_writer(headWriterName);
                someGroup.setYear_formed(yearFormed);
                totalAE.add(someGroup);
                totalWG.add(someGroup);
                someAuthEntity = someGroup;
                valid = true;
            }
            //all inputs are invalid. re-enter the correct Authoring Entity type.
            else {
                System.out.println("Invalid input. Re-enter (Writing Group, Individual Author or Ad Hoc Team):");
                valid = false;
            }
        }
        return someAuthEntity;
    }// end of addAuthoringEntity method

    /**
     * function to get all the publishers primary key.
     */
    public static void getPublisherPK(List<Publishers> primarykey){
        if (primarykey.size() == 0){
            System.out.println("No publisher available");
        }
        else {
            System.out.println("-- Primary keys for Publishers --");
            for (int i = 0; i < primarykey.size(); i++) {
                System.out.println((i + 1) + ".\t" + primarykey.get(i).getName());
            }
        }
    }// end of getPublishersPK method

    /**
     * function to get all the books primary keys
     */
    public static void getBooksPK(List<Books> primarykey){
        if (primarykey.size() == 0){
            System.out.println("No book available");
        }
        else{
            System.out.println("-- Primary keys for Books --");
            for (int i = 0; i < primarykey.size(); i++){
                System.out.println((i+1) +".\t" + primarykey.get(i).getISBN());
            }
        }
    }// end of getBooksPK method

    /**
     * function to get all the authoring entity primary keys
     */
    public static void getAuthoringEntityPK(List<AuthoringEntities> primarykey){
        if (primarykey.size() == 0){
            System.out.println("No authoring entity available");
        }
        else{
            System.out.println("-- Primary keys for Authoring Entity --");
            for (int i = 0; i < primarykey.size(); i++){
                System.out.println((i+1) + ".\t"+ primarykey.get(i).getEmail());
            }
        }
    }// end of getAuthoringEntityPK method

    /**
     * Function check for existing publisher name
     * @param totalPublishers
     * @return a string of non-existing publisher name.
     */
    public static String checkPublisherName(List<Publishers> totalPublishers){
        Scanner scnr = new Scanner(System.in);
        boolean validPubName = false;
        String pubName = "";
        System.out.println("Enter Publisher name:");
        boolean done = false;
        while (!validPubName) {
            pubName = scnr.nextLine();
            done = false;
            //checking if name already exists
            while (!done) {
                for (int i = 0; i < totalPublishers.size(); i++) {
                    if (totalPublishers.get(i).getName().equalsIgnoreCase(pubName)) {
                        System.out.println("That Publisher already exists! Re-enter a new Publisher name.");
                        validPubName = false;
                        done = true;
                    }
                }
                if (done == false){
                    done = true;
                    validPubName = true;
                }
            }
        }
        return pubName;
    } //end of checkPublisherName method

    /**
     * Function check for valid ISBN in books
     * @param totalBooks
     * @return a string of a non-existing ISBN
     */
    public static String checkISBN(List<Books> totalBooks){
        Scanner scnr = new Scanner(System.in);
        boolean validBookISBN = false;
        String ISBN = "";
        boolean donedone = false;
        System.out.println("Enter Book's ISBN:");
        while (!validBookISBN) {
            ISBN = scnr.nextLine();
            donedone = false;
            //checking if book already exists
            while (!donedone) {
                for (int i = 0; i < totalBooks.size(); i++) {
                    if (totalBooks.get(i).getISBN().equals(ISBN)) {
                        System.out.println("That Book already exists! Re-enter a new Book ISBN.");
                        validBookISBN= false;
                        donedone = true;
                    }
                }
                if (donedone == false){
                    donedone = true;
                    validBookISBN = true;
                }
            }
        }
        return ISBN;
    }//end of checkISBN method

    /**
     * Function check for valid/existing Ad Hoc Team Email.
     * @param newMember
     * @param totalAdHocTeams
     */
    public static void checkAdHocEmail(AdHocTeamMembers newMember, List<AdHocTeams> totalAdHocTeams){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter Ad Hoc Team email: ");
        String teamEmail = "";
        //check if ad hoc team email is valid
        boolean endList = false;
        while(!endList){
            teamEmail = scnr.nextLine();
            for (int i = 0; i < totalAdHocTeams.size(); i++) {
                if (totalAdHocTeams.get(i).getEmail().equalsIgnoreCase(teamEmail)) {
                    AdHocTeams existingAdHoc = totalAdHocTeams.get(i);
                    newMember.setAdhocteam(existingAdHoc);
                    endList = true;
                }
            }
            if (endList == false) {
                System.out.println("Try again. Enter a valid email above.");
            }
        }
    }// end of checkAdHocEmail method
    /**
     * Function show all the ad hoc teams available
     * @param allTeams
     */
    public static void showAllAdHocTeam(List<AdHocTeams> allTeams){
        for (int i = 0; i < allTeams.size(); i++) {
            if (allTeams.size() == 0){
                System.out.println("No ad hoc teams available");
            }
            else{
                System.out.println(allTeams.get(i));
            }
        }
    } // End of showAllAdHocTeam method
    /**
     * Function to display books information
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

                if (books.get(i).getISBN().equalsIgnoreCase(bookName)) {
                    System.out.println(books.get(i).toString());
                    found = true;
                    done = true;
                }
            }
            if (!found) {
                System.out.println("Invalid ISBN. Please Re-enter ISBN: ");
            }
        }
    }//end of display_books method
    /** This function shows the user a list of all available books so they can choose
     * which to delete from the database.
     *
     * @param books This is a list of books in the database.
     */
    public static void delete_book(List<Books> books) {
        Scanner scnr = new Scanner(System.in);
        String deleteISBN = "";
        //prints out all available books for user to see
        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available.");
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;
            }
            System.out.println("Enter the ISBN of the book you want to delete: ");
            deleteISBN = scnr.nextLine();

            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getISBN().equals(deleteISBN)) {
                    books.remove(i);
                    System.out.println("Book successfully deleted.");
                }
            }
        }
    }// end of delete_book method
    /**
     * Function to update books
     * @param books
     * @param totalIA
     * @param totalAHT
     * @param totalWG
     * @param totalMems
     * @param totalAE
     */
    public static void update_book(List<Books> books, List<IndividualAuthors> totalIA, List<AdHocTeams> totalAHT, List<WritingGroups> totalWG, List<AdHocTeamMembers> totalMems, List<AuthoringEntities> totalAE) {
        Scanner scnr = new Scanner(System.in);
        boolean validISBN = false;
        boolean flag = false;
        String findISBN = "";
        String updatedType = "";

        //prints out all available books for user to see
        System.out.println("-----Books Information----");
        if (books.size() == 0){
            System.out.println("No books available.");
            validISBN = true;
        }
        else{
            for (int i = 0; i < books.size(); i++){
                System.out.println((i+1) + ". \t" + books.get(i).getISBN()) ;
            }
        }
        //making sure user enters valid isbn
        while(!validISBN) {
            System.out.println("Enter the ISBN of the book you want to update: ");
            findISBN = scnr.nextLine();

            for (int i = 0;  i < books.size(); i++) {
                if (books.get(i).getISBN().equals(findISBN)) {
                    //valid input!
                    flag = true;
                    validISBN = true;
                    AuthoringEntities authEntity = addAuthoringEntity(totalIA, totalAHT, totalWG, totalMems, totalAE);
                    books.get(i).setAuthoring_entity_name(authEntity);
                }
            }
            //invalid input! if not found in booklist, prompts user to reenter an isbn
            if (flag == false) {
                System.out.println("Invalid ISBN.");
            }
        }
    }// end of update_book method

    /**
     * @return a valid integer
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
    }// end of getInt method

    /**
     * Funtion list all the publisher information
     * @param publishers
     */
    public static void list_publisher_info(List<Publishers> publishers){
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
        //checkPublisherName(publishers);
        String pubName = "";
        boolean validInput = false;

        while (!validInput) {
            pubName = scnr.nextLine();
            boolean found = false;
            for (int i = 0; i < publishers.size(); i++) {
                if (publishers.get(i).getName().equalsIgnoreCase(pubName)) {
                    System.out.println(publishers.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }
            if (!found) {
                System.out.println("Publisher not found. Re-enter Publisher name: ");
            }
        }
    } //end of list_publisher_info method
    /**
     * Function to list all the writing group information
     * @param wg
     */
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
                if (wg.get(i).getName().equalsIgnoreCase(pubName)) {
                    System.out.println(wg.get(i).toString());
                    found = true;
                    validInput = true;
                }
            }
            if (!found) {
                System.out.println("Writing Group not found. Re-enter Writing Group name: ");
            }
        }
    }//end of list_writing_group_info method

    /**
     * Checks if user's input for author's email is valid.
     * @param email if email exists in database
     * @param author if author email already exists within the database
     * @return true if the email is valid
     */

    public static boolean getValidAuthorEmail(String email, List<AuthoringEntities> author){
        Scanner scnr = new Scanner(System.in);
        String email2 = email;
        boolean validInput = false;
        boolean done = true;
        boolean found =false;
        while (!found){
            while (!validInput) {
                for (int i = 0; i < author.size(); i++) {
                    if (author.get(i).getEmail().equalsIgnoreCase(email2)) {
                        System.out.println("Email already exist. Re-enter another email.");
                        found = false;
                        done = false;
                    }
                }
                if (done == true){
                    validInput = true;
                    found = true;
                }
                else {
                    validInput = false;
                    done = true;
                    email2 = scnr.nextLine();
                }
            }
        }
        if (found){
            return true;
        }

        return false;
    } //end of checkEmail method
} //end of main

