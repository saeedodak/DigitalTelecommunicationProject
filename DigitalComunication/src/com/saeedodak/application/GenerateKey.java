package com.saeedodak.application;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saeed on 7/4/17.
 */

public class GenerateKey {

    private static int bitLength;
    private static BigInteger Prime;
    private static BigInteger alpha;
    private static BigInteger phi;
    private static List <BigInteger> factors;

    public GenerateKey() {}

    public GenerateKey(int bitLength) {
        GenerateKey.bitLength = bitLength;
        try {
        	do {
        		Prime = BigInteger.probablePrime(bitLength, new SecureRandom());
        		phi = Prime.subtract(BigInteger.ONE);
        		factors = factorization(phi);
    			alpha = findProbablePrimeRoot(Prime);
        	}
        	while(alpha.equals(BigInteger.ZERO) == true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

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

	public List <BigInteger> factorization(BigInteger number) {
	    BigInteger n = number;
		BigInteger cnt = new BigInteger("2");
	   	List <BigInteger> F = new ArrayList <BigInteger> ();
	   	while(n.equals(BigInteger.ONE) == false) {
	   		F.add(cnt);
			while(n.mod(cnt).equals(BigInteger.ZERO) == true) n = n.divide(cnt);
			if(n.isProbablePrime(100) == true) {
				F.add(n);
				return F;
			}
			cnt = cnt.add(BigInteger.ONE);
			if(cnt.equals(new BigInteger("10000")) == true) return F;
		}
	   	return F;
	}

	boolean isPrimeRoot(BigInteger Alpha, BigInteger prime) {
		for(int i = 0; i < factors.size(); i++) {
			BigInteger factor = factors.get(i);
			BigInteger t = phi.divide(factor);
			if(mod_power(Alpha, t, prime).equals(BigInteger.ONE) == true) return false;
		}
		return true;
	}

	BigInteger findProbablePrimeRoot(BigInteger prime) {
		for(int i=1000001; i<1100001; i++) {
			System.out.println(i);
			if(isPrimeRoot(new BigInteger(""+i), prime) == true) {
				System.out.println("find");
				return new BigInteger(""+i);
			}
		}
 		return BigInteger.ZERO;
	}

    public BigInteger getPrime() {
        return Prime;
    }

    public BigInteger getAlpha() {
        return alpha;
    }

    public int getBitLength() {
        return bitLength;
    }

}