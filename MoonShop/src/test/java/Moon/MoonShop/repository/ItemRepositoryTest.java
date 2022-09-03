package moon.moonshop.repository;

import moon.moonshop.domain.item.Item;
import moon.moonshop.repository.item.ItemRepository;
import moon.moonshop.repository.member.MemberRepositoryV3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepositoryV3 memberRepository;


    @Test
    public void save() throws Exception {

        //given
        Item item = new Item();
        item.setItemName("goldenBlue");
        item.setSeller("moon2");
        item.setPrice(70000);
        item.setQuantity(100);
        item.setCategory("alcohol");

        //when
        Item savedItem = itemRepository.save(item);

        //then
        assertThat(savedItem.getItemName()).isEqualTo(item.getItemName());
    }

    @Test
    public void findByItemName() throws Exception {
        //given
        Optional<Item> findItem1 = itemRepository.findByItem("james", "moon1");
        Optional<Item> findItem2 = itemRepository.findByItem("james", "moon2");

        assertThat(findItem1).isNotEmpty();
        assertThat(findItem2).isEmpty();
    }

    @Test
    @Transactional
    public void update() throws Exception {
        //given
        Optional<Item> item = itemRepository.findByItem("james", "moon1");

        Item updateItem = item.get();
        updateItem.setPrice(100000);
        updateItem.setQuantity(150);

        //when
        itemRepository.updateItem(updateItem);
        //then
        Optional<Item> findUpdate = itemRepository.findByItem("james", "moon1");

        assertThat(findUpdate.get().getPrice()).isEqualTo(100000);
        assertThat(findUpdate.get().getQuantity()).isEqualTo(150);
    }
     
     @Test
     @Transactional
     public void remove() throws Exception {


         Optional<Item> findItem = itemRepository.findByItem("james", "moon1");

         itemRepository.removeItem(findItem.get());

         Optional<Item> byItem = itemRepository.findByItem("james", "moon1");
         assertThat(byItem).isEmpty();

      }

}