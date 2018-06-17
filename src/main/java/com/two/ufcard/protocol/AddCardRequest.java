package com.two.ufcard.protocol;

import com.two.ufcard.protocol.api.Request;
import com.two.ufcard.protocol.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCardRequest extends Request {
    private CardDto card;
}
