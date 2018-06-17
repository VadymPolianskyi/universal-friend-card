package com.two.ufcard.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CardDto implements Dto{
    private String id;
    private String owner; //owner in real life
    private String number;
    private String userId; //user from app
}
