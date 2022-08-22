package moon.moonshop.domain.member;


import lombok.Getter;
import moon.moonshop.domain.Address;

@Getter
public class Member {

    private Long id;

    private String userId;
    private String password;

    private String userName;
    private String email;

    private Address address;

//    private String tel;

}
