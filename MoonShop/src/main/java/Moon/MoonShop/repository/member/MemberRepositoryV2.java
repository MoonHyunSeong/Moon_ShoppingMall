package moon.moonshop.repository.member;

import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class MemberRepositoryV2 {

    private NamedParameterJdbcTemplate template;
    // jdbctemplate을 이름으로 받는 것을 구현한 구현체다. 이 방법이 좋은 것 같다. 직관적이고.
    private RowMapper<Member> rowMapper = BeanPropertyRowMapper.newInstance(Member.class);
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public MemberRepositoryV2(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");
    }

    /* 새로운 멤버 저장 */
    public Member save(Member member) {

        // 이 방법을 이용하려면 테이블 필드 이름들과 도메인에 존재하는 이름들이 전부 같아야 한다.
        SqlParameterSource param = new BeanPropertySqlParameterSource(member);

        Number key = jdbcInsert.executeAndReturnKey(param);
        member.setMemberId(key.longValue());
        return member;

    }

    public void removeId(String userId) {
        String sql = "delete from member where userId =:userid";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userid", userId);
        template.update(sql, param);
    }

    public Optional<Member> findByUserId(String userId) {
        String sql = "select * from member where userid = :userid";
        // 끝에 있는 userid 랑 map에서 Key 값으로 줘야하는 userid 하고 이름이 같아야 한다.
        // 그리고 where userid = :userid"; <-이렇게 써줘야한다. :, 띄워쓰기가 굉장히 중요하다.

        try {
            Map<String, Object> param = Map.of("userid", userId);
            Member member = template.queryForObject(sql, param, rowMapper);
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void updatePw(String userId, String pw) {
        String sql = "update member " +
                "set password=:password " +
                "where userid=:userid";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("password", pw)
                .addValue("userid", userId);
        template.update(sql, param);
    }

    public Optional<Member> pay(String userId, Integer totalPrice) {

        String sql = "update member " +
                "set point=:point " +
                "where userid=:userid";
        Optional<Member> member = findByUserId(userId);

        //잔액이 부족할 경우 -> go to service
        if (member.get().getPoint() < totalPrice) {
            return Optional.empty();
        }

        Integer point = member.get().getPoint() - totalPrice;

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("point", point)
                .addValue("userid", userId);
        template.update(sql, param);

        Optional<Member> result = findByUserId(userId);

        return result;

    }

}
