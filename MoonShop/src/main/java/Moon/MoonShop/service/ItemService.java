package moon.moonshop.service;

import lombok.RequiredArgsConstructor;
import moon.moonshop.domain.item.Item;
import moon.moonshop.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * update 를 여기다가 일단 넣어보자.
     */

//    public void updateItem(Long itemId, Item updateParam) {
//        Optional<Item> findItem = itemRepository.findById(itemId);
//
//        findItem.setItemName(updateParam.getItemName());
//        findItem.setPrice(updateParam.getPrice());
//        findItem.setStockQuantity(updateParam.getStockQuantity());
//
//    }

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

//    public void updateItem(String itemName) {
//        Optional<Item> findItem = itemRepository.findByName(itemName);
//
//    }


}
