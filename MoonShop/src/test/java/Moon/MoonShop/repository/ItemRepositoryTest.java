package moon.moonshop.repository;

import moon.moonshop.domain.item.Item;
import moon.moonshop.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save() throws Exception {
        //given
        Item item = new Item();
        item.setItemName("itemA");
        item.setPrice(10000);
        item.setStockQuantity(100);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        org.junit.jupiter.api.Assertions.assertEquals(item.getItemName(),savedItem.getItemName());

     }

    @Test
    public void FindByName() throws Exception {
        //given
        Item item = new Item();
        item.setItemName("itemA");
        item.setPrice(10000);
        item.setStockQuantity(100);

        //when
        Optional<Item> itemA = itemRepository.findByName("itemA");
        //then

        assertThat(itemA.get().getItemName()).isEqualTo(item.getItemName());

    }

    @Test
    public void FindAll() throws Exception {
        //given
        List<Item> byAll = itemRepository.findByAll();
        for (Item item : byAll) {
            System.out.println("item = " + item);
        }
        //when


        //then

     }
     

}