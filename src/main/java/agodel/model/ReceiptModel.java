package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "receipt")
public class ReceiptModel {

    @Id
    @Column(name = "receipt_id", length = 10)
    private String receiptId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        UNAPPROVED, UNPAID, CANCELLED, PAID, COMPLETED
    }

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "date_pay")
    private Date datePay;

    @Column(name = "date_check_in")
    private Date dateCheckIn;

    @Column(name = "date_check_out")
    private Date dateCheckOut;

    @Column(name = "day_count")
    private Integer dayCount;

    @Column(name = "price")
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private RoomModel roomModelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerModel customerId;

    public ReceiptModel() {
    }

    public void setStatus(String status) {
        if (status.equals("UNAPPROVED")) {
            this.status = Status.UNAPPROVED;
        } else if (status.equals("UNPAID")) {
            this.status = Status.UNPAID;
        } else if (status.equals("CANCELLED")) {
            this.status = Status.CANCELLED;
        } else if (status.equals("PAID")) {
            this.status = Status.PAID;
        } else if (status.equals("COMPLETED")) {
            this.status = Status.COMPLETED;
        }
    }

    //override equals of status enum
    public boolean equalsStatus(String status) {
        if (status.equals("UNAPPROVED")) {
            return this.status.equals(Status.UNAPPROVED);
        } else if (status.equals("UNPAID")) {
            return this.status.equals(Status.UNPAID);
        } else if (status.equals("CANCELLED")) {
            return this.status.equals(Status.CANCELLED);
        } else if (status.equals("PAID")) {
            return this.status.equals(Status.PAID);
        } else if (status.equals("COMPLETED")) {
            return this.status.equals(Status.COMPLETED);
        }
        return false;
    }

}