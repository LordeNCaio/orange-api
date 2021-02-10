package com.caiomacedo.orangeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be empty or null")
    private String name;

    @NotBlank(message = "Email cannot be empty or null")
    @Email(message = "Email must be email@email.com")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "CPF cannot be empty or null")
    @Pattern(regexp = "(([0-9]{3}[.]){2}[0-9]{3}[-][0-9]{2})", message = "CPF must be 000.000.000.-00")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "Born date cannot be empty or null")
    @Past(message = "Born date must be in past")
    @Column(name = "born_at")
    private LocalDate bornAt;

    @JsonIgnoreProperties("person")
    @OneToMany(targetEntity = Vaccine.class, cascade = CascadeType.ALL)
    private List<Vaccine> vaccines;

    public Person() {
    }

    public Person(String name, String email, String cpf, LocalDate bornAt) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.bornAt = bornAt;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBornAt() {
        return this.bornAt;
    }

    public void setBornAt(LocalDate bornAt) {
        this.bornAt = bornAt;
    }

    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void addVaccines(Vaccine vaccine) {
        this.vaccines.add(vaccine);
    }
}
