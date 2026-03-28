package hellokim.shopping.modules.item.repository;


import hellokim.shopping.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class itemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // 멀티쓰레드는 해시맵 말고
    private static long sequence = 0L;

    public Item save(Item it){
        it.setId(++sequence);
        store.put(it.getId(), it);
        return it;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }


    public Item update(Long itemId, Item updateParam){ // 여기서 애매한게 updateParam 전용 dto 객체를 만들어야함 혼란스러움을 막아줄 수 있다.
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

        return findItem;
    }

    public void clearStore(){
        store.clear();
    }

} // 또한 item 폴더 안에 따로 폴더도 안만들었다 프로젝트가 작기도 해서
//추가로 만들었다면 모듈 당 테스트 케이스도 만드는게 좋을 거 같다
