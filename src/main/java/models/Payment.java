package models;

public class Payment {
    private String orderId;
    private String cardNumber;
    private String expYear;
    private String expMonth;
    private String cvv;

    public Payment(String orderId, String cardNumber, String expYear, String expMonth, String cvv) {
        this.orderId = orderId;
        this.cardNumber = cardNumber;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.cvv = cvv;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "{"
                + " \"orderId\":\"" + orderId + "\""
                + ", \"cardNumber\":\"" + cardNumber + "\""
                + ", \"expYear\":\"" + expYear + "\""
                + ", \"expMonth\":\"" + expMonth + "\""
                + ", \"cvv\":\"" + cvv + "\""
                + "}";
    }
}
