package hellokim.shopping.modules.item.controller;


import hellokim.shopping.domain.item.Item;
import hellokim.shopping.modules.item.repository.itemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class restItemController {

    private final itemRepository itRe;

    //1 조회
    @GetMapping
    public List<Item> items(){
        return itRe.findAll();
    }

    //2.특정 조회
    @GetMapping("/{itemId}")
    public Item item(@PathVariable long itemId){
        return itRe.findById(itemId);
    }

    //3.상품 등록
    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        return itRe.save(item);
    }

    //4.상품 수정
    @PostMapping("/edit/{itemId}")
    public Item edit(@PathVariable long itemId, @RequestBody Item item){
        return itRe.update(itemId, item);
    }




}
