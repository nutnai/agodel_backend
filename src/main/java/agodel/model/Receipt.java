package agodel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @Column(name = "receiptID", length = 10)
    private String receiptID;

    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "dateCreate")
    private Instant dateCreate;

    @Column(name = "datePay")
    private Instant datePay;

    @Column(name = "dateCheckIn")
    private Instant dateCheckIn;

    @Column(name = "dateCheckOut")
    private Instant dateCheckOut;

    @Column(name = "dayCount")
    private Short dayCount;

    @Column(name = "price")
    private Short price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomID")
    private Room roomID;

}