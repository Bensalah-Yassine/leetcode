import heapq

class Solution:
    def minCost(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        
        val_map = {}
        for r in range(m):
            for c in range(n):
                val = grid[r][c]
                if val not in val_map:
                    val_map[val] = []
                val_map[val].append((r, c))
        
        sorted_vals = sorted(val_map.keys())
        
        pq = [(0, 0, 0, k)]
        

        inf = float('inf')
        dist = [[[inf] * n for _ in range(m)] for _ in range(k + 1)]
        dist[k][0][0] = 0
        

        processed_idx = [-1] * (k + 1)
        
        while pq:
            d, r, c, rem_k = heapq.heappop(pq)
            

            if d > dist[rem_k][r][c]:
                continue
            
            if r == m - 1 and c == n - 1:
                return d
            

            for dr, dc in [(0, 1), (1, 0)]:
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n:
                    new_cost = d + grid[nr][nc]
                    if new_cost < dist[rem_k][nr][nc]:
                        dist[rem_k][nr][nc] = new_cost
                        heapq.heappush(pq, (new_cost, nr, nc, rem_k))
            
            if rem_k > 0:
                target_k = rem_k - 1
                current_val = grid[r][c]
                
                idx = processed_idx[target_k] + 1
                
                while idx < len(sorted_vals):
                    val = sorted_vals[idx]
                    
                    if val > current_val:
                        break
                    
                    for tr, tc in val_map[val]:
                        if d < dist[target_k][tr][tc]:
                            dist[target_k][tr][tc] = d
                            heapq.heappush(pq, (d, tr, tc, target_k))
                    
                    processed_idx[target_k] = idx
                    idx += 1
                    
        return -1