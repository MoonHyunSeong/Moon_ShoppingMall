package moon.moonshop.domain.member;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    @NotNull
    private Long memberId;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    private Integer point;

    private String city;
    private String street;
    private String zipcode;

}
