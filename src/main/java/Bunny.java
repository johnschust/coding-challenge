public class Bunny {

    public int bunnyHops(int n) {
        int totalCombinations = 0;
        for(int count = 1; count <=3; count++) {
            if(count <= n) {
                if(count == n) {
                    totalCombinations += 1;
                    break;
                } else {
                    totalCombinations += bunnyHops(n - count);
                }
            }
        }
        return totalCombinations;
    }
}
