package hellokim.shopping.domain.item;

import hellokim.shopping.modules.item.repository.itemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class itemRepositoryTest {

    itemRepository itemRepository = new itemRepository();


    @AfterEach
    void after(){
        itemRepository.clearStore();
    }


    @Test
    void save(){
        //given
        Item it = new Item("itemA", 10000, 10);
        //when
        Item save = itemRepository.save(it);
        //then
        Item findedItem = itemRepository.findById(it.getId());

        assertThat(findedItem).isEqualTo(save);
    }

    @Test
    void findAll(){
        //given
        Item it1 = new Item("itemA", 10000, 10);
        Item it2 = new Item("itemB", 30000, 20);
        //when
        List<Item> result = itemRepository.findAll();

        //then

        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    void updateItem(){
        //given
        Item it1 = new Item("itemA", 10000, 10);
        Item saved = itemRepository.save(it1);
        Long itemId = saved.getId();


        //when

        Item it2 = new Item("itemB", 30000, 20);
        itemRepository.update(itemId, it2);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(it2.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(it2.getPrice());
    }

}