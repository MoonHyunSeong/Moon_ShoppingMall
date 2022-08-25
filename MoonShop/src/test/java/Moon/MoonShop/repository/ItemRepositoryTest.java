package moon.moonshop.repository;

import moon.moonshop.domain.item.Category;
import moon.moonshop.domain.item.Item;
import moon.moonshop.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepositoryV2 memberRepository;


    @Test
    @Rollback(value = false)
    public void save() throws Exception {

        Optional<Member> moon2 = memberRepository.findByUserId("moon2");

        //given
        Item item = new Item("james", 30000, 50, "alcohol", moon2.get().getUserId());

        Item save = itemRepository.save(item);
        //when

        //then

    }

}