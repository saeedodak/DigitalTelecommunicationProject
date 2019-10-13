package com.saeedodak.application;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Project {

	public static BigInteger Prime;
	public static BigInteger alpha;

	public static KeyExchangeDiffieHellman DH_A, DH_B;

	public static byte[] encryptionKey;

	public static String decrypted_str;

	public static String cipher_str;

	public static String plain_str;

    public static void genrator() {
    	GenerateKey foo = new GenerateKey(256);
        Prime = foo.getPrime();
        alpha = foo.getAlpha();
        DH_A = new KeyExchangeDiffieHellman(Prime, alpha);
        DH_B = new KeyExchangeDiffieHellman(Prime, alpha);
        DH_A.generateCommonKey(DH_B.publicKey);
//      DH_B.generateCommonKey(DH_A.publicKey);
        encryptionKey = ((DH_A.commonKey).toString()).getBytes(StandardCharsets.UTF_8);
    }

    public static void send(String text) {
    	byte[] plainText = text.getBytes(StandardCharsets.UTF_8);
        AdvancedEncryptionStandard advancedEncryptionStandard = new AdvancedEncryptionStandard(encryptionKey);
        try {
            byte[] cipherText = advancedEncryptionStandard.encrypt(plainText);
            byte[] decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
            plain_str = new String(plainText);
            cipher_str = new String(cipherText);
            decrypted_str = new String(decryptedCipherText);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}