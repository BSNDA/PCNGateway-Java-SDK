package com.bsnbase.sdk.util.sm2;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECFieldElement.Fp;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.SecureRandom;


public class SM2Factory {
    /*-----------------------Parameters related to state algorithm begin-----------
     * ------------------*/
    //A The First Factor
    private static final BigInteger a  = new BigInteger("fffffffeffffffffffffffffffffffffffffffff00000000fffffffffffffffc",16);
    //B The Second Factor
    private static final BigInteger b  = new BigInteger("28e9fa9e9d9f5e344d5a9e4bcf6509a7f39789f515ab8f92ddbcbd414d940e93",16);
    //Curve X Coefficient
    private static final BigInteger gx = new BigInteger("32c4ae2c1f1981195f9904466a39c9948fe30bbff2660be1715a4589334c74c7",16);
    //Curve Y Coefficient
    private static final BigInteger gy = new BigInteger("bc3736a2f4f6779c59bdcee36b692153d0a9877cc62a474002df32e52139f0a0",16);
    //Producer Order Coeffiencient
    private static final BigInteger n  = new BigInteger("fffffffeffffffffffffffffffffffff7203df6b21c6052b53bbf40939d54123",16);
    //Prime Number
    private static final BigInteger p  = new BigInteger("fffffffeffffffffffffffffffffffffffffffff00000000ffffffffffffffff",16);
    //Factor Coefficient 1
    private static final int h  = 1;
    /*-----------------------Parameter related to State algorithm end-----------------------------*/
    //Some necessary classes
    public final ECFieldElement ecc_gx_fieldelement;
    public final ECFieldElement ecc_gy_fieldelement;
    public final ECCurve ecc_curve;
    public final ECPoint ecc_point_g;
    public final ECDomainParameters ecc_bc_spec;
    public final ECKeyPairGenerator ecc_key_pair_generator;
    /**
     * Initialize method
     * @return
     */
    public static SM2Factory getInstance(){
        return new SM2Factory();
    }
    public SM2Factory() {

        this.ecc_gx_fieldelement = new Fp(this.p,this.gx);
        this.ecc_gy_fieldelement = new Fp(this.p, this.gy);

        this.ecc_curve = new ECCurve.Fp(this.p, this.a, this.b);

        this.ecc_point_g = new ECPoint.Fp(this.ecc_curve, this.ecc_gx_fieldelement,this.ecc_gy_fieldelement);
        this.ecc_bc_spec = new ECDomainParameters(this.ecc_curve, this.ecc_point_g, this.n);

        ECKeyGenerationParameters ecc_ecgenparam;
        ecc_ecgenparam = new ECKeyGenerationParameters(this.ecc_bc_spec, new SecureRandom());

        this.ecc_key_pair_generator = new ECKeyPairGenerator();
        this.ecc_key_pair_generator.init(ecc_ecgenparam);
    }
    /**
     * Calculate Z according to the private key and curve parameters
     * @param userId
     * @param userKey
     * @return
     */
    public  byte[] sm2GetZ(byte[] userId, ECPoint userKey){
        SM3Digest sm3 = new SM3Digest();

        int len = userId.length * 8;
        sm3.update((byte) (len >> 8 & 0xFF));
        sm3.update((byte) (len & 0xFF));
        sm3.update(userId, 0, userId.length);

        byte[] p = Util.byteConvert32Bytes(this.a);
        sm3.update(p, 0, p.length);

        p = Util.byteConvert32Bytes(this.b);
        sm3.update(p, 0, p.length);

        p = Util.byteConvert32Bytes(this.gx);
        sm3.update(p, 0, p.length);

        p = Util.byteConvert32Bytes(this.gy);
        sm3.update(p, 0, p.length);

        p = Util.byteConvert32Bytes(userKey.normalize().getXCoord().toBigInteger());
        sm3.update(p, 0, p.length);

        p = Util.byteConvert32Bytes(userKey.normalize().getYCoord().toBigInteger());
        sm3.update(p, 0, p.length);

        byte[] md = new byte[sm3.getDigestSize()];
        sm3.doFinal(md, 0);
        return md;
    }
    /**
     * Signature correlation value calculation
     * @param md
     * @param userD
     * @param userKey
     * @param sm2Result
     */
    public void sm2Sign(byte[] md, BigInteger userD, ECPoint userKey, SM2Result sm2Result) {
        BigInteger e = new BigInteger(1, md);
        BigInteger k = null;
        ECPoint kp = null;
        BigInteger r = null;
        BigInteger s = null;
        do {
            do {
                // Formal environment
                AsymmetricCipherKeyPair keypair = ecc_key_pair_generator.generateKeyPair();
                ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) keypair.getPrivate();
                ECPublicKeyParameters ecpub = (ECPublicKeyParameters) keypair.getPublic();
                k = ecpriv.getD();
                kp = ecpub.getQ();
                //System.out.println("BigInteger:" + k + "\nECPoint:" + kp);

                //System.out.println("calculated curve pointX1: "+ kp.getXCoord().toBigInteger().toString(16));
                //System.out.println("calculated curve pointY1: "+ kp.getYCoord().toBigInteger().toString(16));
                //System.out.println("");
                // r
                r = e.add(kp.getXCoord().toBigInteger());
                r = r.mod(this.n);
            } while (r.equals(BigInteger.ZERO) || r.add(k).equals(this.n)||r.toString(16).length()!=64);

            // (1 + dA)~-1
            BigInteger da_1 = userD.add(BigInteger.ONE);
            da_1 = da_1.modInverse(this.n);
            // s
            s = r.multiply(userD);
            s = k.subtract(s).mod(this.n);
            s = da_1.multiply(s).mod(this.n);
        } while (s.equals(BigInteger.ZERO)||(s.toString(16).length()!=64));

        sm2Result.r = r;
        sm2Result.s = s;
    }
    /**
     * verify signature
     * @param md sm3 summary
     * @param userKey An ecpoint object based on the public key decode 
     * @param r No particular meaning
     * @param s No particular meaning
     * @param sm2Result Object that receives the parameter
     */
    public void sm2Verify(byte md[], ECPoint userKey, BigInteger r,
                          BigInteger s, SM2Result sm2Result) {
        sm2Result.R = null;
        BigInteger e = new BigInteger(1, md);
        BigInteger t = r.add(s).mod(this.n);
        if (t.equals(BigInteger.ZERO)) {
            return;
        } else {
            ECPoint x1y1 = ecc_point_g.multiply(sm2Result.s);
            //System.out.println("calculated curve pointX0: "+ x1y1.normalize().getXCoord().toBigInteger().toString(16));
            //System.out.println("calculated curve pointY0: "+ x1y1.normalize().getYCoord().toBigInteger().toString(16));
            //System.out.println("");

            x1y1 = x1y1.add(userKey.multiply(t));
            //System.out.println("calculated curve pointX1: "+ x1y1.normalize().getXCoord().toBigInteger().toString(16));
            //System.out.println("calculated curve pointY1: "+ x1y1.normalize().getYCoord().toBigInteger().toString(16));
            //System.out.println("");
            sm2Result.R = e.add(x1y1.normalize().getXCoord().toBigInteger()).mod(this.n);
            //System.out.println("R: " + sm2Result.R.toString(16));
            return;
        }
    }

}