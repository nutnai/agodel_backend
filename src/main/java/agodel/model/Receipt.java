package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @Column(name = "receipt_id", length = 10)
    private String receiptId;

    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "date_create")
    private String dateCreate;

    @Column(name = "date_pay")
    private String datePay;

    @Column(name = "date_check_in")
    private String dateCheckIn;

    @Column(name = "date_check_out")
    private String dateCheckOut;

    @Column(name = "day_count")
    private int dayCount;

    @Column(name = "price")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private RoomModel roomModelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id")
    private CustomerModel customerId;

}