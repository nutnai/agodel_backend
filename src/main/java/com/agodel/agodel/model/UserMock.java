package com.agodel.agodel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class UserMock {
    private String name, faculty, phone, email, desInj, desAcc, placeTreat, typeHos, medicalFeeNum, bankAcc,
            dateAcc, medicalFeeLetter;
    @Id
    private String student_id;

    public UserMock() {
    }
}
