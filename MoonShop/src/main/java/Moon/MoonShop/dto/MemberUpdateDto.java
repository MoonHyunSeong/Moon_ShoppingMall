package moon.moonshop.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberUpdateDto {

    @NotEmpty
    private String password;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String point;

    private String city;
    private String street;
    private Integer zipcode;
}
