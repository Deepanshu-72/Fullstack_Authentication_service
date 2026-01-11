package org.example.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {
  // this class dilever request from client api -> server  for data ( in this case Refresh token)
  // asking for refresh token
    public String token;
}
