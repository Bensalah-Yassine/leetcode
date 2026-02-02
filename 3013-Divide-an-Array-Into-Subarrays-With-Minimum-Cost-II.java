import java.util.TreeMap;

class Solution {
    TreeMap<Integer, Integer> L = new TreeMap<>(), R = new TreeMap<>();
    int szL = 0;
    long sumL = 0;

    void add(int x, int k) {
        L.put(x, L.getOrDefault(x, 0) + 1);
        sumL += x;
        szL++;
        if (szL > k) {
            int m = L.lastKey();
            if (L.put(m, L.get(m) - 1) == 1) L.remove(m);
            sumL -= m;
            szL--;
            R.put(m, R.getOrDefault(m, 0) + 1);
        }
    }

    void rem(int x) {
        if (R.containsKey(x)) {
            if (R.put(x, R.get(x) - 1) == 1) R.remove(x);
        } else {
            if (L.put(x, L.get(x) - 1) == 1) L.remove(x);
            sumL -= x;
            szL--;
            if (!R.isEmpty()) {
                int m = R.firstKey();
                if (R.put(m, R.get(m) - 1) == 1) R.remove(m);
                L.put(m, L.getOrDefault(m, 0) + 1);
                sumL += m;
                szL++;
            }
        }
    }

    public long minimumCost(int[] nums, int k, int dist) {
        long ans = Long.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            add(nums[i], k - 1);
            if (i >= dist + 2) rem(nums[i - dist - 1]);
            if (i >= dist + 1) ans = Math.min(ans, sumL);
        }
        return ans + nums[0];
    }
}