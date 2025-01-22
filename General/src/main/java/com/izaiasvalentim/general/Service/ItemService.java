package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceAlreadyExistsException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
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
                throw new ResourceAlreadyExistsException("Item with name " + item.getName() + " already exists. " +
                        "Try add stock for this item.");
            }
            item.setDeleted(false);
            var savedItem = itemRepository.save(item);
            ItemUtils.generateItemBatch(savedItem);

            return itemRepository.save(savedItem);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException(e.getMessage());
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
                throw new ResourceNotFoundException("Item with name " + itemDTO.getName() + " does not exist.");
            }
        } catch (Exception e) {
            throw new ErrorInProcessServiceException(e.getMessage());
        }
    }

    public List<Item> getAllItemsByName(String name) {
        return itemRepository.findAllByName(name).orElse(List.of());
    }

    public boolean deleteItemStockByBatch(String batch) {
        try {
            var batchToDelete = itemRepository.findByBatch(batch).orElse(null);
            if (batchToDelete != null) {
                itemRepository.delete(batchToDelete);
                return true;
            } else {
                throw new ResourceNotFoundException("Item with name " + batch + " does not exist.");
            }
        } catch (Exception e) {
            throw new ErrorInProcessServiceException(e.getMessage());
        }

    }

}
