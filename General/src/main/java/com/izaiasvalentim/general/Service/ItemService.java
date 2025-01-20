package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.utils.ItemUtils;
import com.izaiasvalentim.general.Domain.DTO.Item.ItemAddStockDTO;
import com.izaiasvalentim.general.Domain.Item;
import com.izaiasvalentim.general.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item registerNewItem(Item item) {
        try {
            if (itemRepository.findFirstByName(item.getName()).isPresent()) {
                // É para ser gerada uma exceção personalizada. ItemAlreadyExists.
                return null;
            }
            item.setDeleted(false);
            var savedItem = itemRepository.save(item);
            ItemUtils.generateItemBatch(savedItem);

            return itemRepository.save(savedItem);
        } catch (Exception e) {
            return null;
        }
    }

    public Item addItemStock(ItemAddStockDTO itemDTO) {
        try {
            Item validateItem = itemRepository.findFirstByName(itemDTO.getName()).orElse(null);
            if (validateItem != null) {

                Item newItem = new Item();
                newItem.setName(validateItem.getName());
                newItem.setType(validateItem.getType());
                newItem.setPrice(itemDTO.getPrice());
                newItem.setQuantity(itemDTO.getQuantity());
                newItem.setCode(validateItem.getCode());
                newItem.setValidity(itemDTO.getValidity());
                newItem.setHasValidity(itemDTO.getHasValidity());

                var savedItem = itemRepository.save(newItem);
                ItemUtils.generateItemBatch(savedItem);

                return itemRepository.save(savedItem);
            } else {
                // Deve retornar exception
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public List<Item> getAllItemsByName(String name) {
        return itemRepository.findAllByName(name).orElse(List.of());
    }

    public void deleteItemStockByBatch(String batch) {

        var batchToDelete = itemRepository.findByBatch(batch).orElse(null);
        if (batchToDelete != null) {
            itemRepository.delete(batchToDelete);
        } else {
            //Gerar exception.
        }
    }

}
