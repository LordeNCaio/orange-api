package com.caiomacedo.orangeapi.entity;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "born_at")
    private Date bornAt;

    @OneToMany(targetEntity = Vaccine.class, cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

    public Person() {}

    public Person(String name, String email, String cpf, Date bornAt) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.bornAt = bornAt;
    }

    public Integer getId() { return this.id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return this.email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return this.cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public Date getBornAt() { return this.bornAt; }
    public void setBornAt(Date bornAt) { this.bornAt = bornAt; }

    public List<Vaccine> getVaccines() { return vaccines; }
    public void setVaccines(List<Vaccine> vaccines) { this.vaccines = vaccines; }
    public void addVaccines(Vaccine vaccine) { this.vaccines.add(vaccine); }
}
