import java.util.*;

class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int fullMask = (1 << n) - 1;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];

        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            q.offer(new int[]{i, mask});
            visited[i][mask] = true;
        }

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] cur = q.poll();
                int node = cur[0];
                int mask = cur[1];

                if (mask == fullMask) {
                    return steps;
                }

                for (int nei : graph[node]) {
                    int nextMask = mask | (1 << nei);
                    if (!visited[nei][nextMask]) {
                        visited[nei][nextMask] = true;
                        q.offer(new int[]{nei, nextMask});
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}
