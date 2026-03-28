package hellokim.shopping.modules.item.controller;


import hellokim.shopping.domain.item.Item;
import hellokim.shopping.modules.item.repository.itemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {


    private final itemRepository itRe;

    @GetMapping
    public String items(Model mo){
        List<Item> Items = itRe.findAll();
        mo.addAttribute("items", Items);
        return "basic/items"; // 템플릿에 있는 basic 안에 있는 items 로드
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct // 모든 스프링 빈과 객체 생성 그리고 서로 연결이 끝난 후 이 함수 실행하라는 뜻
    public void init(){
        itRe.save(new Item("a",100,1));
        itRe.save(new Item("b",200,3));
    }


    @GetMapping("/{itemId}")
    public String item (@PathVariable long itemId, Model mo){ // @PathVariable =  URL 경로에 포함된 값을 변수처럼 추출
        Item it = itRe.findById(itemId);
        mo.addAttribute("item", it);
        return "basic/item";

    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }


    @PostMapping("/add1")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int prive,
                       @RequestParam Integer quantity,
                       Model mo){
        Item it = new Item();
        it.setItemName(itemName);
        it.setQuantity(quantity);
        it.setPrice(prive);
        itRe.save(it);

        return "basic/item";

    }

    @PostMapping("/add") // 사용자가 보낸 데이터를 자바 객체(DTO/VO)로 통째로 매핑하고, 그 객체를 View(HTML/JSP 등)까지 자동으로 전달 (model변수 자동 생성, 넣기) jso n 이면 requestBody 하면 된다
    public String addItemV2(@ModelAttribute("item")Item it, Model mo){

        itRe.save(it);
        return "basic/item";

    }
    //추가로 modelAttribute도 줄일 수 있다 근데 그건 너무 간거 같긴함



    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itRe.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit") public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itRe.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }




}


//get "DB에서 꺼내서 나한테 줘"

//post "이거 줄 테니 DB에 넣어"


