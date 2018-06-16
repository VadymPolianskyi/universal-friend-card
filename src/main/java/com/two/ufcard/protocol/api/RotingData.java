package com.two.ufcard.protocol.api;

import com.two.ufcard.dao.entity.security.UserCredentials;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RotingData {
    private UserCredentials userCredentials;
}
