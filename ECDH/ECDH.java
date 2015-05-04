/**
 * Created by asus on 2015/2/7.
 */
public class ECDH {
    private long p;
    private long g;
    private long priKey;
    private long pubKey;
    private static long MAX = 10000;
    private long aesKey;
    private static long getRand(long mod){
        long now = System.nanoTime();
        if (now < 0) now *= -1;
        return now % mod + 1;
    }
    private static boolean isPrime(long num){
        if (num <= 1) return false;
        long tmp = (long) Math.sqrt((double)num);
        for (long i = 2; i <= tmp ; i++){
            if (num % i == 0) return false;
        }
        return true;
    }
    public static long generateP(){
        long ans = 0;
        while(!isPrime(ans = getRand(MAX)));
        return ans;
    }
    public static long generateG(long P){
        return getRand(P-1);
    }
    public long Pow(long a,long b){
        long ans = 1;
        while(b != 0){
            if ( b % 2 == 1){
                ans *= a;
                ans %= p;
            }
            a *= a;
            a %= p;
            b /=2;
        }
        return ans;
    }
    public ECDH(long P,long G){
        p = P;
        g = G;
        priKey = getRand(MAX);
        pubKey = Pow(g,priKey);
    }
    public long generateAes(long pk){
        return aesKey = Pow(pk,priKey);
    }
    public long getAes(){
        return aesKey;
    }
    public long getPubkey(){
        return pubKey;
    }
}
