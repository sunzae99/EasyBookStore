package com.easybookstore.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.easybookstore.backend.enums.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    private Long isbn;

    @Column(nullable = false)
    private String name;

    // @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ElementCollection //used when we need to add a list of item;
    private List<genre> genre= new ArrayList<>();;

    private Integer publishedYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "authId")
    private Author author;
}
