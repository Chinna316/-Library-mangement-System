package database;

import com.mongodb.*;
import models.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MongoHandler {
    private static final String ORDERS = "checkin_out";
    private static final String CATEGORY = "category";
    private static final String PUBLISHER = "publisher";
    private static final String PAYMENT = "payment";
    private static final String AUTHORS = "author";
    private MongoClient mongoClient;
    private DB database;
    public static final String DB = "library";
    public static final String BOOKS = "books";
    private static MongoHandler instance;

    private MongoHandler() throws UnknownHostException {
        mongoClient = new MongoClient();
        database = mongoClient.getDB(DB);
        String[] categories = new String[]{"Fiction", "IT", "Mathematics", "Drama", "Physics"};
        DBCollection collection = database.getCollection(CATEGORY);

        for (String category : categories) {
            if (collection.findOne(new BasicDBObject("category", category)) == null) {
                collection.insert(new BasicDBObject("category", category));
            }
        }
    }

    public static MongoHandler getInstance() {
        try {
            if (instance == null) {
                instance = new MongoHandler();
            }
            return instance;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getBooks(int count) {
        DBCollection collection = database.getCollection(BOOKS);
        DBCursor books = collection.find().limit(count);
        return parseBooks(books);
    }


    private List<Book> parseBooks(DBCursor books) {
        List<Book> booksList = new ArrayList<>();
        for (DBObject book : books) {
            booksList.add(parseBook(book));
        }
        return booksList;
    }

    private List<Category> parseCategories(DBCursor categories) {
        List<Category> categoriesList = new ArrayList<>();
        for (DBObject category : categories) {
            categoriesList.add(parseCategory(category));
        }
        return categoriesList;
    }


    private Book parseBook(DBObject book) {
        ObjectId id = (ObjectId) book.get("_id");
        String isbn = (String) book.get("isbn");
        String title = (String) book.get("title");
        int stock = (int) book.get("stock");
        Author author = getAuthor((ObjectId)book.get("author"));
        String edition = (String) book.get("edition");
        Publisher publisher = getPublisher((ObjectId) book.get("publisher"));
        String categoryId = (String) book.get("category");
        DBCollection collection = database.getCollection(CATEGORY);
        DBObject categoryObj = collection.findOne(new BasicDBObject("_id", new ObjectId(categoryId)));
        return new Book(id, isbn, title, stock, author, edition, publisher, parseCategory(categoryObj));
    }

    private Author getAuthor(ObjectId author) {
        System.out.println("Author: "+author);
        DBCollection collection = database.getCollection(AUTHORS);
        DBObject object = collection.findOne(new BasicDBObject("_id", author));
        return parseAuthor(object);
    }

    private Publisher getPublisher(ObjectId publisher) {
        DBCollection collection = database.getCollection(PUBLISHER);
        DBObject object = collection.findOne(new BasicDBObject("_id", publisher));
        return parsePublisher(object);
    }

    private Category parseCategory(DBObject category) {
        return new Category((String) category.get("category"));
    }

    private int getInteger(String string) {
        Scanner in = new Scanner(string).useDelimiter("[^0-9]+");
        int integer = in.nextInt();
        return integer;
    }

    public Book searchBookByISBN(String isbn) {
        DBCollection collection = database.getCollection(BOOKS);
        DBObject book = collection.findOne(new BasicDBObject("isbn", isbn));
        return parseBook(book);
    }

    public List<Book> getBooksByCategory(String category) {
        DBCollection collection = database.getCollection(BOOKS);
        ObjectId objectId = addCategory(category);
        System.out.println("Category ID: "+ objectId);
        DBCursor books = collection.find(new BasicDBObject("category", objectId.toString()));
        return parseBooks(books);
    }

    public List<Category> getCategories(){
        DBCollection collection = database.getCollection(CATEGORY);
        DBCursor categories = collection.find();
        return parseCategories(categories);
    }



    public List<Book> getBooksBySearch(String query) {
        DBCollection collection = database.getCollection(BOOKS);
        ArrayList<BasicDBObject> orList = new ArrayList<>();
        orList.add(new BasicDBObject("title", new BasicDBObject("$regex", query)));
        orList.add(new BasicDBObject("author", new BasicDBObject("$regex", query)));
        orList.add(new BasicDBObject("isbn", new BasicDBObject("$regex", query)));
        BasicDBObject queryDB = new BasicDBObject("$or", orList);
        DBCursor books = collection.find(queryDB).limit(10);
        return parseBooks(books);
    }

    public boolean lendBook(User user, String isbn, Date date) {
        DBCollection collection = database.getCollection(ORDERS);
        Book book = searchBookByISBN(isbn);
        if (book.getStock() < 1)
            return false;
        DBObject object = new BasicDBObject();
        object.put("user", user.getEmail());
        object.put("book_isbn", isbn);
        object.put("returnDate", date);
        object.put("status", "pending");
        WriteResult insert = collection.insert(object);
        return true;
    }

    public List<Order> getMyOrders(String email) {
        DBCollection collection = database.getCollection(ORDERS);
        DBCursor orderControllers = collection.find(new BasicDBObject("user", email));
        System.out.println(orderControllers);
        return parseOrders(orderControllers);
    }


    private List<Order> parseOrders(DBCursor orderControllers) {
        List<Order> orderList = new ArrayList<>();
        for (DBObject order : orderControllers) {
            System.out.println(order);
            orderList.add(parseOrder(order));
        }
        return orderList;
    }

    private Order parseOrder(DBObject order) {
        ObjectId id = (ObjectId) order.get("_id");
        String user = (String) order.get("user");
        String bookISBN = (String) order.get("book_isbn");
        Date retDate = (Date) order.get("returnDate");
        String status = (String) order.get("status");
        Order mOrder = new Order(id, user, bookISBN, retDate, status);
        if (order.get("updatedDate") != null) {
            Date updatedDate = (Date) order.get("updatedDate");
            mOrder.setUpdatedDate(updatedDate);
        }

        if (order.get("issueDate") != null) {
            Date issueDate = (Date) order.get("issueDate");
            mOrder.setIssueDate(issueDate);
        }


        if (order.get("returnedOn") != null) {
            Date updatedDate = (Date) order.get("returnedOn");
            mOrder.setReturnedOn(updatedDate);
        }

        return mOrder;
    }


    public boolean addAuthor(Author publisher) {
        DBCollection collection = database.getCollection(AUTHORS);
        DBObject object = new BasicDBObject();
        object.put("name", publisher.getName());
        object.put("email", publisher.getEmail());
        object.put("address", publisher.getAddress());
        object.put("mobile", publisher.getMobile());
        collection.insert(object);
        return true;
    }


    public boolean addPublisher(Publisher publisher) {
        DBCollection collection = database.getCollection(PUBLISHER);
        DBObject object = new BasicDBObject();
        object.put("name", publisher.getName());
        object.put("email", publisher.getEmail());
        object.put("address", publisher.getAddress());
        object.put("mobile", publisher.getMobile());
        collection.insert(object);
        return true;
    }

    public List<Publisher> getPublishers() {
        DBCollection collection = database.getCollection(PUBLISHER);
        List<Publisher> publishers = new ArrayList<>();
        for (DBObject object : collection.find()) {
            publishers.add(parsePublisher(object));
        }
        return publishers;
    }

    public List<Author> getAuthors() {
        DBCollection collection = database.getCollection(AUTHORS);
        List<Author> publishers = new ArrayList<>();
        for (DBObject object : collection.find()) {
            publishers.add(parseAuthor(object));
        }
        return publishers;
    }

    private Author parseAuthor(DBObject object) {
        ObjectId id = (ObjectId) object.get("_id");
        String name = (String) object.get("name");
        String address = (String) object.get("address");
        String email = (String) object.get("email");
        String mobile = (String) object.get("mobile");
        return new Author(id, name, address, email, mobile);
    }

    private Publisher parsePublisher(DBObject object) {
        ObjectId id = (ObjectId) object.get("_id");
        String name = (String) object.get("name");
        String address = (String) object.get("address");
        String email = (String) object.get("email");
        String mobile = (String) object.get("mobile");
        return new Publisher(id, name, address, email, mobile);
    }

    public boolean addBook(Book book, String publisher, String author) {
        DBCollection collection = database.getCollection(BOOKS);
        DBObject object = new BasicDBObject();
        object.put("isbn", book.getIsbn());
        object.put("title", book.getTitle());
        object.put("stock", book.getStock());
        object.put("author", new ObjectId(author));
        object.put("edition", book.getEdition());
        object.put("publisher", new ObjectId(publisher));
        ObjectId category = addCategory(book.getCategory().getCategory());
        object.put("category", category.toString());
        object.put("createdAt", new Date());
        collection.insert(object);
        return true;
    }

    private ObjectId addCategory(String category) {
        DBCollection collection = database.getCollection(CATEGORY);
        DBObject object = collection.findOne(new BasicDBObject("category", category));
        if (object == null) {
            collection.insert(new BasicDBObject("category", category));
            object = collection.findOne(new BasicDBObject("category", category));
        }
        return (ObjectId) object.get("_id");
    }

    public List<Order> getAllOrders() {
        DBCollection collection = database.getCollection(ORDERS);
        DBCursor orders = collection.find();
        System.out.println(orders);
        return parseOrders(orders);
    }

    public void updateOrder(String orderId, String status) {
        DBCollection collection = database.getCollection(ORDERS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(orderId));
        DBObject obj = collection.findOne(query);
        obj.put("status", status);
        obj.put("updatedDate", new Date());
        obj.put("issueDate", new Date());


        String bookIsbn = (String) obj.get("book_isbn");
        if (status.equals("accepted")) {
            DBCollection bookCollection = database.getCollection(BOOKS);

            BasicDBObject bookQuery = new BasicDBObject();
            bookQuery.put("isbn", bookIsbn);

            DBObject book = bookCollection.findOne(bookQuery);
            int stock = (int) book.get("stock");
            book.put("stock", --stock);
            BasicDBObject isbn = new BasicDBObject();
            isbn.put("isbn", bookIsbn);
            bookCollection.update(isbn, book);
        }

        WriteResult writeResult = collection.update(query, obj);
        System.out.println(writeResult);
    }

    public void deleteBook(String isbn) {
        DBCollection collection = database.getCollection(BOOKS);
        BasicDBObject query = new BasicDBObject();
        query.put("isbn", isbn);
        WriteResult remove = collection.remove(query);
    }

    public boolean updateBook(String id, Book book, String author, String publisher) {
        DBCollection collection = database.getCollection(BOOKS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        DBObject object = collection.findOne(query);
        object.put("isbn", book.getIsbn());
        object.put("title", book.getTitle());
        object.put("stock", book.getStock());
        object.put("edition", book.getEdition());
        ObjectId objectId = addCategory(book.getCategory().getCategory());
        object.put("category", objectId.toString());
        object.put("author", new ObjectId(author));
        object.put("publisher", new ObjectId(publisher));

        WriteResult writeResult = collection.update(query, object);
        System.out.println(writeResult);
        return true;
    }


    public Order getOrderById(String orderId) {
        DBCollection collection = database.getCollection(ORDERS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(orderId));
        DBObject object = collection.findOne(query);
        return parseOrder(object);
    }

    public void retBook(String orderId, Payment payment) {
        DBCollection collection = database.getCollection(ORDERS);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(orderId));
        DBObject obj = collection.findOne(query);
        obj.put("status", "returned");
        obj.put("returnedAt", new Date());
        ObjectId paymentId = addPayment(payment);
        obj.put("payment", paymentId);
        WriteResult writeResult = collection.update(query, obj);
        String bookIsbn = (String) obj.get("book_isbn");
        DBCollection bookCollection = database.getCollection(BOOKS);
        BasicDBObject bookQuery = new BasicDBObject();
        bookQuery.put("isbn", bookIsbn);
        DBObject book = bookCollection.findOne(bookQuery);
        int stock = (int) book.get("stock");
        book.put("stock", ++stock);
        BasicDBObject isbn = new BasicDBObject();
        isbn.put("isbn", bookIsbn);
        bookCollection.update(isbn, book);

    }

    private ObjectId addPayment(Payment payment) {
        DBCollection collection = database.getCollection(PAYMENT);
        DBObject object = new BasicDBObject();
        object.put("card_num", payment.getCardNumber());
        object.put("cvv", payment.getCvv());
        object.put("exp_month", payment.getExpMonth());
        object.put("exp_year", payment.getExpYear());
        object.put("order", payment.getOrderId());
        collection.insert(object);
        DBObject obj = collection.findOne(new BasicDBObject("card_num", payment.getCardNumber()));
        return (ObjectId) obj.get("_id");
    }


    public boolean addCategoryToDB(String name) {
        addCategory(name);
        return true;
    }
}