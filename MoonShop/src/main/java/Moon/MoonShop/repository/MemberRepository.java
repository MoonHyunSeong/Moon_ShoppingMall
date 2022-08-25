package moon.moonshop.repository;

import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;
//    private RowMapper<Member> memberRowMapper = BeanPropertyRowMapper.newInstance(Member.class);


    @Autowired
    public MemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*private static Map<Long, Member> store = new ConcurrentHashMap<>();

    // map을 통해 회원들 id 기준으로 저장
    private static long sequence = 0L; // uuid를 통한 고유번호 제공 방법? 고민*/

    public Member save(Member member) throws SQLException {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("member_id");
        // member_id를 pk로 사용하겠다 하는 부분인듯

        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("member_id", keyHolder);
        parameters.put("member_userid", member.getUserId());
        parameters.put("member_password", member.getPassword());
        parameters.put("member_username", member.getUserName());
        // 이게 좀 잘 모르겠는데 아무튼 필드에 맞게 값을 부여하는 부분이라고 생각.

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // 키 값에 대응하여 테이블에 행을 통으로 넣어주는 부분이라고 생각.

        member.setId(key.longValue());
        // member 에 id값 전달. -> 현재 도메인에 setter가 존재하는데 이것이 문제가 될 수 있는지는 고민이 필요.

        return member;
    }

    public Optional<Member> findById(Long user_id) {
        //고유 id로 회원 찾아오기.
        List<Member> result = jdbcTemplate.query("select * from member where member_id = ?", memberRowMapper(), user_id);
        return result.stream().findAny();
    }

    public List<Member> findByAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
//        // 훗날 관리자 페이지를 위해서 남겨두겠다.
//        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        List<Member> result = jdbcTemplate.query("select * from member where member_userid = ?", memberRowMapper(), loginId);
        return result.stream().findAny();
    }

    public void updatePassword(Member member, String newPw) {
        jdbcTemplate.update("update member set member_password =? " +
                "where member_userId =?", newPw, member.getUserId());
    }

    private RowMapper<Member> memberRowMapper() {
        //멤버 테이블에 행을 만들어주는 듯 하다.

        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("member_id"));
            member.setUserId(rs.getString("member_userid"));
            member.setPassword(rs.getString("member_password"));
            member.setUserName(rs.getString("member_username"));
            return member;
        };
    }
}
