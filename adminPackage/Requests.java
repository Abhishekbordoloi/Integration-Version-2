package adminPackage;

public class Requests {
    int request_id, classified_id, priceOffered;
    String buyer, seller, status;

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public int getClassified_id() {
        return classified_id;
    }

    public void setClassified_id(int classified_id) {
        this.classified_id = classified_id;
    }

    public int getPriceOffered() {
        return priceOffered;
    }

    public void setPriceOffered(int priceOffered) {
        this.priceOffered = priceOffered;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        String s = this.request_id + " " + this.buyer + " " + this.seller + " " + this.classified_id + " " +
                this.priceOffered + " " + this.status;
        return s;
    }
}
