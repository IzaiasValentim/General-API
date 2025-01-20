package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.DTO.Item.ItemAddStockDTO;
import com.izaiasvalentim.general.Domain.DTO.Item.ItemDTO;
import com.izaiasvalentim.general.Domain.Item;
import com.izaiasvalentim.general.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "allByName")
    public ResponseEntity<?> getAllItems(@RequestParam String name) {
        var listItems = itemService.getAllItemsByName(name);

        return new ResponseEntity<>(listItems, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> addItem(@RequestBody ItemDTO dto) {
        var item = Item.itemDTOToItem(dto);
        var savedItem = itemService.registerNewItem(item);

        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PostMapping(value = "addStockByName/")
    public ResponseEntity<?> addItemStockByName(@RequestBody ItemAddStockDTO dto) {
        var savedItem = itemService.addItemStock(dto);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "deleteByBatch")
    public ResponseEntity<?> deleteItemByBatch(@RequestParam String batch) {
        itemService.deleteItemStockByBatch(batch);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
