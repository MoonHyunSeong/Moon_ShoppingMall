package moon.moonshop.repository;

import lombok.extern.slf4j.Slf4j;
import moon.moonshop.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new ConcurrentHashMap<>();
    // map을 통해 회원들 id 기준으로 저장
    private static long sequence = 0L; // uuid를 통한 고유번호 제공 방법? 고민

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        //고유 id로 회원 찾아오기.
        return store.get(id);
    }

    public List<Member> findByAll() {
        // 훗날 관리자 페이지를 위해서 남겨두겠다.
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findByAll().stream()
                .filter(m -> m.getUserId().equals(loginId))
                .findFirst();
    }

    public void clearStore() {
        // for test code
        store.clear();
    }


}
