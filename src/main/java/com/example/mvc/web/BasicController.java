package com.example.mvc.web;

import com.example.mvc.domin.Item;
import com.example.mvc.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능, 이름바꾸면 안됨
        return "basic/item";
    }

    //modelattribute -> 1. 요청 파라미터 처리, 2.model 추가

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능, 이름바꾸면 안됨 , 이름지우면 클래스 이름의 앞글자 소문자로 해서 인식함
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item){
        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능, 이름바꾸면 안됨 , 이름지우면 클래스 이름의 앞글자 소문자로 해서 인식함
        return "basic/item";
    }
    //자동 적용 됨

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }


    /**
     * 테스트용
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }

}
