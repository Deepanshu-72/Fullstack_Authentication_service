package org.example.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {

// this class will diliver dto to client throught controler in the request they had made
    private String accessToken;  // acess token

    private String token; // returning Refresh token
}
