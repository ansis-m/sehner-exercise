package com.example.sehnerbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "postgres_id")
    private Long postgresId;

    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "founded_on")
    private LocalDate foundedOn;

    @Column(name = "revenue")
    private Double revenue;

    @Column(name = "website")
    private String website;

    @Column(name = "category")
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return getPostgresId() != null && Objects.equals(getPostgresId(), company.getPostgresId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
