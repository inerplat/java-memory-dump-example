package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
// not recommend, but useful for nalmuk
@AllArgsConstructor
@Builder
@Table(name = "users", indexes = {
        @Index(name = "idx_user_name", columnList = "name", unique = true),
        @Index(name = "idx_user_cnt", columnList = "cnt", unique = false),
})
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String randomString;
    private int cnt;

    public static UserEntity deserialize(String s) {
        String[] split = s.split(", ");
        return UserEntity.builder()
                .id(Long.parseLong(split[0].split("=")[1]))
                .name(split[1].split("=")[1])
                .randomString(split[2].split("=")[1])
                .cnt(Integer.parseInt(split[3].split("=")[1].replace(")", "")))
                .build();
    }
}
