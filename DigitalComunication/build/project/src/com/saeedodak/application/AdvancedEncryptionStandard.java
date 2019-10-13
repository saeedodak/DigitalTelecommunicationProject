package com.saeedodak.application;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AdvancedEncryptionStandard {

    private byte[] key;

    public AdvancedEncryptionStandard(byte[] key) {
        byte[] EK = new byte[16];
        for(int i=0; i<16; i++) EK[i] = key[i];
        this.key = EK;
    }

    /**
     * Encrypts the given plain text
     * @param plainText The plain text to encrypt
     */
    public byte[] encrypt(byte[] plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plainText);
    }

    /**
     * Decrypts the given byte array
     * @param cipherText The data to decrypt
     */
    public byte[] decrypt(byte[] cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(cipherText);
    }

}