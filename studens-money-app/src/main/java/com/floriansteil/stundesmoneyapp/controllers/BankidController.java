package com.floriansteil.stundesmoneyapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.floriansteil.stundesmoneyapp.database.Bankid;
import com.floriansteil.stundesmoneyapp.database.BankidTable;

import com.floriansteil.stundesmoneyapp.vault.CryptoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.Versioned;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankidController {

    private final String secureServiceAddress = System.getenv("SECURE_SERVICE_ADDRESS");
    private final String kvMountPath          = System.getenv("VAULT_KV2_MOUNT");
    private final String apiKeyPath           = System.getenv("VAULT_API_KEY_PATH");
    private final String apiKeyField          = System.getenv("VAULT_API_KEY_FIELD");
    @Autowired
    private BankidTable table;
    @Autowired
    private VaultTemplate vaultTemplate;

    @GetMapping("/bankid")
    public ResponseEntity<List<Bankid>> Bankid() {

        List<Bankid> bankids = table.findAll();

        if ( bankids == null || bankids.size() == 0 ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Versioned<Map<String, Object>> response = vaultTemplate
                .opsForVersionedKeyValue(kvMountPath)
                .get(apiKeyPath);

        if (response != null && response.hasData()) {
            String apiKey = (String) Objects.requireNonNull(response.getData()).get(apiKeyField);
            CryptoClass cryptoClass = new CryptoClass();
            for (Bankid bankid : bankids) {

                String encryptedIban = cryptoClass.decrypt(bankid.getIban(), apiKey);
                bankid.setIban(encryptedIban);
            }
        }

        return new ResponseEntity<>(bankids, HttpStatus.OK);
    }
    @PostMapping("/bankid")
    public ResponseEntity<String> NewBankID (@RequestBody Bankid newBankid){

        Versioned<Map<String, Object>> response = vaultTemplate
                .opsForVersionedKeyValue(kvMountPath)
                .get(apiKeyPath);

        if (response != null && response.hasData()) {

            String apiKey = (String) Objects.requireNonNull(response.getData()).get(apiKeyField);

            CryptoClass cryptoClass = new CryptoClass();
            String cryptedIban = cryptoClass.encrypt(newBankid.getIban(), apiKey);
            newBankid.setIban(cryptedIban);
            Bankid tmpResponseEntity = table.save(newBankid);
            return new ResponseEntity<>("{\n" +
                    "    \"id\": 2\n" +
                    "}", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
