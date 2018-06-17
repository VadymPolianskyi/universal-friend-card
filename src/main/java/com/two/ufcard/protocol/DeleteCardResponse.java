package com.two.ufcard.protocol;

import com.two.ufcard.protocol.api.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCardResponse extends Response {
    private boolean deleted;
}
