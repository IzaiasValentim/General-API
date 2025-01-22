package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ErrorInProcessServiceException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceAlreadyExistsException;
import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Domain.Item;
import com.izaiasvalentim.general.Domain.Resource;
import com.izaiasvalentim.general.Repository.ItemRepository;
import com.izaiasvalentim.general.Repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository, ItemRepository itemRepository) {
        this.resourceRepository = resourceRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void createResourceAfterInitialItem(Item item) {
        try {
            if (itemRepository.findFirstByName(item.getName()).isEmpty()) {
                throw new ResourceAlreadyExistsException("Item with name " + item.getName() + " not exists. " +
                        "The resource cannot be registered.");
            }

            Resource newResource = new Resource();
            newResource.setItems(List.of(item));
            newResource.setItemCode();
            newResource.setName();
            newResource.setStock(item.getQuantity());

            var savedResource = resourceRepository.save(newResource);
            item.setResource(savedResource);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while creating resource after item. " + e.getMessage());
        }
    }

    @Transactional
    public void updateResourceAfterChangedItemStock(Item item) {
        try {
            var resourceToUpdate = resourceRepository.findByItemCode(item.getCode()).orElse(null);
            if (resourceToUpdate == null) {
                throw new ResourceNotFoundException("Resource with item code " + item.getCode() + " not exists. " +
                        "The resource cannot be updated.");
            }

            List<Item> newListItems = setItemOnList(resourceToUpdate.getItems(), item);

            resourceToUpdate.setItems(newListItems);
            resourceToUpdate.setStock(calculateTotalStockOfItems(newListItems));

            var updatedResource = resourceRepository.save(resourceToUpdate);
            item.setResource(updatedResource);
        } catch (Exception e) {
            throw new ErrorInProcessServiceException("Error while creating resource after item. " + e.getMessage());
        }
    }

    private List<Item> setItemOnList(List<Item> itemList, Item item) {
        if (!item.isDeleted()) {
            itemList.add(item);
        } else {
            itemList.remove(item);
        }
        return itemList;
    }

    private Double calculateTotalStockOfItems(List<Item> items) {
        return items.stream().map(Item::getQuantity).reduce(0.0, Double::sum);
    }

}
