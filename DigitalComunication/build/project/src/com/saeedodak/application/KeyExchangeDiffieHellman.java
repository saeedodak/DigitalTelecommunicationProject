package com.saeedodak.application;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Saeed on 7/4/17.
 */

/**
 * class to make key exchange instance
 * fields : Prime, alpha, private Key and public key
 */
public class KeyExchangeDiffieHellman {

    public BigInteger Prime;
    public BigInteger alpha;
    public BigInteger publicKey;
    private BigInteger privateKey;
    public BigInteger secondPartyPublicKey;
    public BigInteger commonKey;

    public KeyExchangeDiffieHellman() {}

    public KeyExchangeDiffieHellman(BigInteger Prime, BigInteger alpha) {
        this.Prime = Prime;
        this.alpha = alpha;
        generateKeys();
    }
    /**
     * generate private Key and public key
     */
    private void generateKeys() {
        privateKey = BigInteger.probablePrime(Math.max(Prime.bitLength()-10, 10), new SecureRandom());
        publicKey = mod_power(alpha, privateKey, Prime);
    }

    /**
     * generate
     * @param secondPartyPublicKey second side public key
     */
    public void generateCommonKey(BigInteger secondPartyPublicKey) {
        this.secondPartyPublicKey = secondPartyPublicKey;
        commonKey = mod_power(secondPartyPublicKey, privateKey, Prime);
    }

    /**
     * compute base^N (mod Mod) in O(log N)
     *
     * @param base
     * @param N
     * @param Mod
     */
    private BigInteger mod_power(BigInteger base, BigInteger N, BigInteger Mod) {
        if(N.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;
        BigInteger T = new BigInteger("0");
        T = mod_power(base, N.divide(new BigInteger("2")), Mod);
        T = (T.multiply(T)).remainder(Mod);
        String foo = N.toString();
        int t = foo.charAt(foo.length()-1) - '0';
        if(t%2 == 1) return (T.multiply(base)).remainder(Mod);
        return T;
    }

    public String getPrivateKey() {
        return privateKey.toString();
    }

    public String getPublicKey() {
        return publicKey.toString();
    }

}