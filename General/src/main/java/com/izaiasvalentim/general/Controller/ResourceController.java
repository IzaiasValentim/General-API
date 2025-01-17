package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.Item;
import com.izaiasvalentim.general.Domain.Resource;
import com.izaiasvalentim.general.Repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/resources")

public class ResourceController {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceController(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(resourceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "saveDummyProduct")
    public ResponseEntity<?> saveDummyProduct() {
        var arroz_l1 = new Item("Arroz", "Alimentício", 4.99D, 65D, "L01", "CAL01",
                new Date(), true, false);
        var arroz_l2 = new Item("Arroz", "Alimentício", 4.99D, 23D, "L02", "CAL02",
                new Date(), true, false);
        List<Item> lista_arroz = new ArrayList<>(List.of(arroz_l1, arroz_l2));

        var stock = arroz_l1.getQuantity() + arroz_l2.getQuantity();
        var resource_arroz = new Resource(lista_arroz, stock);
        resource_arroz.setName();
        resource_arroz.setItemCode();

        resourceRepository.save(resource_arroz);
        return new ResponseEntity<>("Salvo", HttpStatus.OK);
    }
}
