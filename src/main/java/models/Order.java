package models;

import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Order {
    private ObjectId id;
    private String user;
    private String bookISBN;
    private Date issueDate;
    private Date retDate;
    private String status;
    private Date updatedDate;
    private Date returnedOn;
    private Payment payment;

    public Order(ObjectId id, String user, String bookISBN, Date retDate, String status) {
        this.id = id;
        this.user = user;
        this.bookISBN = bookISBN;
        this.retDate = retDate;
        this.status = status;
        updatedDate = new Date();
        returnedOn = new Date();
    }

    public Date getReturnedOn() {
        return returnedOn;
    }

    public void setReturnedOn(Date returnedOn) {
        this.returnedOn = returnedOn;
    }

    public Date getUpdatedDate() {
       return updatedDate;
    }

     public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Date getRetDate() {
        return retDate;
    }

    public void setRetDate(Date retDate) {
        this.retDate = retDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getPrice() {
        String price = "";
        Double total = getTotal();
        price = Math.abs(total) + "";
        return price;
    }

    public Double getTotal() {
        double total = 0;
        switch (status) {
            case "returned":
                if (retDate.before(returnedOn)) {
                    double cost = getDays(updatedDate, retDate) * 10;
                    double fine = getDays(retDate, returnedOn) * 0;
                    total = Math.abs(cost) + Math.abs(fine);
                } else {
                    int days = getDays(updatedDate, returnedOn);
                    total = Math.abs(days * 10);
                }
                break;
            case "pending":
                total = 0;
                break;
            case "accepted":
                if (retDate.before(new Date())) {
                    double cost = getDays(updatedDate, retDate) * 10;
                    double fine = getDays(retDate, new Date()) * 0;
                    total = Math.abs(cost) + Math.abs(fine);
                } else {
                    double cost = getDays(updatedDate, new Date()) * 10;
                    total = Math.abs(cost);
                }
                break;
        }
        return Math.abs(total);
    }

    private int getDays(Date start, Date end) {
        long diff = start.getTime() - end.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String getCost() {
        return getPrice();
    }

    public void setCost(String cost) {

    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        String retOn = returnedOn!=null?new SimpleDateFormat("yyyy-MM-dd").format(returnedOn):"Not available";

        if (!status.equalsIgnoreCase("returned")){
            retOn = "Not available";
        }
        String issuedOn = issueDate !=null?issueDate+"":" Not available";

        return "{"
                + "\"id\":\"" + id
                + "\", \"user\":\"" + user
                + "\", \"bookISBN\":\"" + bookISBN + "\""
                + ", \"issueDate\":\"" + issuedOn + "\""
                + ", \"retDate\":\"" + new SimpleDateFormat("yyyy-MM-dd").format(retDate)
                + "\", \"retOn\":\"" + retOn
                + "\", \"status\":\"" + status + "\""
                + ", \"cost\":\"" + getPrice() + "\""
                +", \"payment\":"+getPayment()
                + "}";
    }
}
