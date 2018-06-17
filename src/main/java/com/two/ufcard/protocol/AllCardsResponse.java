package com.two.ufcard.protocol;

import com.two.ufcard.protocol.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllCardsResponse {
    private List<CardDto> cards;
}
