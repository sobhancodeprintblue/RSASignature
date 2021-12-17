/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa_signature;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Time;

/**
 *
 * @author Mohammad
 */
public class RSA_Signature {
    static long l1,l2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException {
        
        // TODO code application logic here
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        
        KeyPairGenerator keyGen=KeyPairGenerator.getInstance("RSA","BC");
        
        keyGen.initialize(512, new SecureRandom());
        
        KeyPair keyPair=keyGen.generateKeyPair();
        Signature signature=Signature.getInstance("SHA1withRSA","BC");
        
        signature.initSign(keyPair.getPrivate(), new SecureRandom());
        
        byte[] message="abc".getBytes();
        signature.update(message);
        l1=System.currentTimeMillis();
        byte[] sigBytes=signature.sign();
        signature.initVerify(keyPair.getPublic());
        signature.update(message);
        System.out.println(sigBytes.toString());
        l2=System.currentTimeMillis()-l1;
        System.out.println("time= "+l2);
    }
    
}
