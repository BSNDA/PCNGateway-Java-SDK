package com.bsnbase.sdk.util.sm2;
import org.bouncycastle.math.ec.ECPoint;
import java.math.BigInteger;

public class SM2Result {
    public SM2Result() {
    }
    // Signature r
    public BigInteger r;
    public BigInteger s;
    //verify the signature R
    public BigInteger R;

    // exchange the key
    public byte[] sa;
    public byte[] sb;
    public byte[] s1;
    public byte[] s2;

    public ECPoint keyra;
    public ECPoint keyrb;
}
