package com.floriansteil.stundesmoneyapp.database;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bankid")
public class Bankid {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentid", nullable = false)
    private Long studentid;

    @Column(name = "iban", nullable = false)
    private String iban;


    public Bankid() {
    }

    public Bankid(Long id, Long studentid, String iban) {

        this.id = id;
        this.studentid = studentid;
        this.iban = iban;

    }

    public Long getId() {
        return id;
    }
    public Long getStudentid() {
        return studentid;
    }

    public void setStudentid(Long studentid) {
        this.studentid = studentid;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.studentid);
        hash = 79 * hash + Objects.hashCode(this.getIban());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Bankid other = (Bankid) obj;

        if (!Objects.equals(this.studentid, other.studentid) && !Objects.equals(this.getIban(), other.getIban())) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Studens {");

        sb.append("id=").append(id);
        sb.append(", studentid='").append(this.studentid).append('\'');
        sb.append(", ibane='").append(this.getIban()).append('\'');
        sb.append('}');

        return sb.toString();
    }

}
