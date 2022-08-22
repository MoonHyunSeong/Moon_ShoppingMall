package moon.moonshop.domain.member;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;

    @NotEmpty
    private String userName;

    private String email;


    private Address address;

    private String tel;


    private Account account;

    public Member(String userId, String password, String userName, String email,
                  Address address, String tel, Account account) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.account = account;
    }
}
