package com.accolite.alertMessenger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Message {

    @Id
    @GeneratedValue
    private int messageId;
    private String aircraftRegistration;
    private String flight;
    private String desk;
    private String deskCategory;
    private String escalated;
    private String acknowledge;
    private String acknowledgedBy;
    private String received;
    private String priority;


}
