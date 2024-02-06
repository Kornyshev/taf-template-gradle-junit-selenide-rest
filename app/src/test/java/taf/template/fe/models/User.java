package taf.template.fe.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String login;
    private String password;

}
