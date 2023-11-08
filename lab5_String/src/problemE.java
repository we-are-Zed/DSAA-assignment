import java.io.*;
import java.util.Arrays;

public class problemE {
    private static final int BASE = 257;
    private static final long MOD1 = 1000000009L;
    private static final long MOD2 = 999998727899999L; // Another large prime

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s1 = br.readLine();
        String s2 = br.readLine();

        int left = 1, right = Math.min(s1.length(), s2.length());
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(s1, s2, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        pw.println(ans);
        pw.flush();
    }

    private static boolean check(String s1, String s2, int length) {
        long[][] hashValuesS1 = getHashValues(s1, length);
        long[][] hashValuesS2 = getHashValues(s2, length);

        // Sort the hash values for s1 for binary search
        Arrays.sort(hashValuesS1, (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0]));

        for (long[] hash : hashValuesS2) {
            if (binarySearch(hashValuesS1, hash)) {
                return true;
            }
        }

        return false;
    }

    private static long[][] getHashValues(String s, int length) {
        long[][] hashValues = new long[s.length() - length + 1][2];

        long hash1 = 0, hash2 = 0;
        long h1 = 1, h2 = 1;
        for (int i = 0; i < length; i++) {
            h1 = (h1 * BASE) % MOD1;
            h2 = (h2 * BASE) % MOD2;
            hash1 = (hash1 * BASE + s.charAt(i)) % MOD1;
            hash2 = (hash2 * BASE + s.charAt(i)) % MOD2;
        }
        hashValues[0][0] = hash1;
        hashValues[0][1] = hash2;

        for (int i = length; i < s.length(); i++) {
            hash1 = (hash1 * BASE - s.charAt(i - length) * h1 + s.charAt(i)) % MOD1;
            hash2 = (hash2 * BASE - s.charAt(i - length) * h2 + s.charAt(i)) % MOD2;
            if (hash1 < 0) hash1 += MOD1;
            if (hash2 < 0) hash2 += MOD2;
            hashValues[i - length + 1][0] = hash1;
            hashValues[i - length + 1][1] = hash2;
        }

        return hashValues;
    }

    private static boolean binarySearch(long[][] list, long[] target) {
        int l = 0, r = list.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (list[mid][0] == target[0] && list[mid][1] == target[1]) {
                return true;
            }
            if (list[mid][0] < target[0] || (list[mid][0] == target[0] && list[mid][1] < target[1])) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
