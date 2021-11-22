package crypto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class cryptoTest {

    @Test
    public void testDecode() {
        assertEquals(2, crypto.decode(33, 7, 29));
        int a= crypto.decode(133, 29, 92);
        assertEquals(99, a);
    }

    @Test
    public void testFind() {
        assertEquals("c", crypto.find(13, 0));
        assertEquals("ab", crypto.find(1112, 0));
        assertEquals("abcde", crypto.find(1112131415, 0));
        assertEquals("abcde", crypto.find(1213141516, 1));
    }

    @Test
    public void testEnDecode() {
        int b= crypto.encode(2024, 212141, 487001);
        assertEquals(480272, b);
        assertEquals(2024, crypto.decode(487001, 212141, b));
    }

}
