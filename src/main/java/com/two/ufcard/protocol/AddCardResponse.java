package com.two.ufcard.protocol;

import com.two.ufcard.protocol.api.Response;
import com.two.ufcard.protocol.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCardResponse extends Response {
    private CardDto card;
}
