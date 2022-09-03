package moon.moonshop.service;

import lombok.RequiredArgsConstructor;
import moon.moonshop.domain.item.Item;
import moon.moonshop.domain.item.ItemUpdateDto;
import moon.moonshop.repository.item.ItemRepository;
import moon.moonshop.repository.member.MemberRepositoryV3;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public String saveItem(Item item) {

        if (item.getPrice() > 999999){
            return "최대 가격을 초과했습니다. 최대 가격 = 999,999";
        }
        if (item.getQuantity() > 999) {
            return "최대 갯수를 초과했습니다. 최대 갯수 = 999 ";
        }

        itemRepository.save(item);
        return "정상적으로 등록되었습니다.";
    }

    public Optional<Item> findByItem(String itemName, String seller) {
        Optional<Item> findItem = itemRepository.findByItem(itemName, seller);
        return findItem;
    }

    /**
     * update 를 위한 dto를 하나 만들어 줘야 한다.
     * 아이템 수정은 가격과 재고 수량밖에 없기 때문이다.
     */
    @Transactional
    public void updateItem(Item item) {
        itemRepository.updateItem(item);
    }

    @Transactional
    public void removeItem(Item item) {
        itemRepository.removeItem(item);
    }



}
