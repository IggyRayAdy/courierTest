package com.example.courier.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ord")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "infomation", nullable = false)
    private String contactInfo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date", updatable = false)
    private LocalDate arrivalDate;
    @Column(name = "done")
    private boolean isDone;
    @Column(name = "active")
    private boolean isActive;

    @OneToOne(mappedBy = "order")
    transient private Task task;

    public Long getId() {
        return id;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setContactInfo(String adressOfOrder) {
        this.contactInfo = adressOfOrder;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate= LocalDate.parse(arrivalDate);
    }

}
