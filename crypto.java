package crypto;

import java.util.HashMap;
import java.util.LinkedList;

public class crypto {

    /** Given a public key, encodes a messages m */
    public static int encode(int m, int e, int n) {
        return PingalaAlgo(m, e, n);
    }

    /** Returns the decrypted message, returns 0 if no prime factors are found */
    public static int decode(int n, int e, int c) {
        int phi;
        int msg;
        for (int p= 2; p < Math.pow(n, 0.5) + 1; p++ ) {
            if (n % p == 0 && isPrime(p) && n % (n / p) == 0 && isPrime(n / p)) {
                int q= n / p;
                phi= (p - 1) * (q - 1);
                if (phi != 0) {
                    int d= modInv(e, phi);
                    msg= PingalaAlgo(c, d, n);
                    return msg;
                }

            }
        }

        return 0;
    }

    /** Creates a mapping of each two digit integer to each char */
    public static HashMap<Integer, String> map(int diff) {
        HashMap<Integer, String> map= new HashMap<Integer, String>();
        char c;
        for (int i= 0; i <= 25; i++ ) {
            c= (char) ('a' + i);
            map.put(11 + i + diff, String.valueOf(c));
        }
        return map;
    }

    /** Returns the secret message given the integer different from default for the map */
    public static String find(int m, int diff) {
        HashMap<Integer, String> map= map(diff);
        LinkedList<String> list= new LinkedList<String>();
        String s;
        int x;
        while (m != 0) {
            x= m % 100;
            s= map.get(x);
            list.addFirst(s);
            m= (m - x) / 100;
            if (m < 10) m= 0;
        }
        return String.join("", list);

    }

    /** Returns the modular inverse of an integer mod phi */
    public static int modInv(int e, int phi) {
        int a= e % phi;
        for (int k= 1; k < phi; k++ ) {
            if ((a * k) % phi == 1) { return k; }
        }
        return 0;

    }

    /** Determines whether an integer is prime */
    public static boolean isPrime(int q) {
        int root= (int) Math.sqrt(q);
        for (int i= 2; i < root + 1; i++ ) {
            if (q % i == 0) { return false; }
        }
        return true;
    }

    /** Uses Pingala's Algorithm to solve for an int c raised to int d mod n */
    public static int PingalaAlgo(int c, int d, int n) {
        long x= c;
        long y= 1;
        while (d > 0) {
            if (d % 2 == 1) {
                y= (x * y) % n;
            }
            x= (x * x) % n;
            d= d / 2;
        }
        return (int) y % n;
    }

}
