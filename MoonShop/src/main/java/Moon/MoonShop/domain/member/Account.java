package moon.moonshop.domain.member;


import lombok.Data;

@Data
public class Account {

    private String account;
    private Integer point;

    protected Account() {
    }

    public Account(String account, Integer point) {
        this.account = account;
        this.point = point;
    }
}
