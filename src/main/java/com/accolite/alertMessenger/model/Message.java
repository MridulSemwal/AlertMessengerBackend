package com.accolite.alertMessenger.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {

    @Id
    @GeneratedValue
    private int messageId;
    @NotBlank(message = "Please enter the value")
    private String aircraftRegistration;
    @NotBlank(message = "Please enter the value")
    private String flight;
    @NotBlank(message = "Please enter the value")
    private String desk;
    @NotBlank(message = "Please enter the value")
    private String deskCategory;
    @NotBlank(message = "Please enter the value")
    private String escalated;
    @Column(columnDefinition = "varchar(255) default 'NO'")
    private String acknowledge;
    @NotBlank(message = "Please enter the value")
    private String acknowledgedBy;
    @NotBlank(message = "Please enter the value")
    private String received;
    @NotBlank(message = "Please enter the value")
    private String priority;
    @Column(columnDefinition = "int default 0")
    private int isPublished;

}
