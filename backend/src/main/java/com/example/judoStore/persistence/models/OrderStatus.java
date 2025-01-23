package com.example.judoStore.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "order_statuses")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class OrderStatus {

    public static String WAITING = "В ожидании";
    public static String SENT = "Отправлен";
    public static String FINISHED = "Завершен";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "orderStatus")
    @JsonIgnore
    private List<Order> orders;

}
