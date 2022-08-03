package moon.moonShop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long memberID;

    private String userId;

    private String password;

    private String email;

    @Embedded
    private Address address;

}
