package moon.moonshop.domain.member;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    @NotNull
    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;

    @NotEmpty
    private String userName;

    @NotEmpty
    private Integer point;

    private String city;
    private String street;
    private String zipcode;

    public Member() {
    }

    public Member(String userId, String password, String userName, Integer point, String city, String street, String zipcode) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.point = point;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
