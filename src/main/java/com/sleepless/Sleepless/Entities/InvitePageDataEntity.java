package com.sleepless.Sleepless.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "inivte_page_data")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class InvitePageDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long counter;
    @Column(unique = true)
    Date date;

    public void incrementCounter() {
        counter++;
    }
}
