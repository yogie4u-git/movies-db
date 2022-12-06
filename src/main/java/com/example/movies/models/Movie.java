package com.example.movies.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Director", nullable = false)
    private String director;

    @Column(name = "ReleaseDate", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;
}
