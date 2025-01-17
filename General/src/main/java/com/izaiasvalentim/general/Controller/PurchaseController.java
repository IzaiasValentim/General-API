package com.izaiasvalentim.general.Controller;

import com.izaiasvalentim.general.Domain.Enums.TypePurchaseStatus;
import com.izaiasvalentim.general.Domain.Purchase;
import com.izaiasvalentim.general.Domain.PurchaseItem;
import com.izaiasvalentim.general.Repository.ApiUserRepository;
import com.izaiasvalentim.general.Repository.PurchaseRepository;
import com.izaiasvalentim.general.Repository.ResourceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/purchases")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
    private final ResourceRepository resourceRepository;
    private final ApiUserRepository apiUserRepository;

    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository, ResourceRepository resourceRepository,
                              ApiUserRepository apiUserRepository) {
        this.purchaseRepository = purchaseRepository;
        this.resourceRepository = resourceRepository;
        this.apiUserRepository = apiUserRepository;
    }

    @GetMapping(value = "getAll")
    public ResponseEntity<?> getAll() {
        List<Purchase> resources = purchaseRepository.findAll();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(value = "saveDummyPurchase")
    @Transactional
    public ResponseEntity<?> saveDummyPurchase() {
        try {
            var resourceItem = resourceRepository.findById(1L).orElse(null);
            var user = apiUserRepository.findById(1L).orElse(null);
            if (user != null && resourceItem != null) {
                // Crie a instância de Purchase
                var purchase = new Purchase(33D, "Dinheiro", user, "Alguem", TypePurchaseStatus.RECEIVED.getId(),
                        new Date(), null, false);

                // Crie o PurchaseItem
                var purchaseItem = new PurchaseItem(resourceItem, 3L);
                // Configure o relacionamento bidirecional
                purchaseItem.setPurchase(purchase);

                // Configure os items na Purchase
                purchase.setPurchaseItems(List.of(purchaseItem));

                // Salve a Purchase (o CascadeType.ALL garante que os items serão salvos juntos)
                purchaseRepository.save(purchase);

                return new ResponseEntity<>(purchase, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
