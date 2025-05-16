import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class Fatorial {

    // Recursivo simples
    public static BigInteger fatorialRecursivo(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(fatorialRecursivo(n - 1));
    }

    // Iterativo
    public static BigInteger fatorialIterativo(int n) {
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

    // Programação dinâmica - Bottom-Up
    public static BigInteger fatorialBottomUp(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1].multiply(BigInteger.valueOf(i));
        }
        return dp[n];
    }

    // Programação dinâmica - Top-Down com memoização
    public static BigInteger fatorialTopDown(int n, HashMap<Integer, BigInteger> memo) {
        if (n == 0 || n == 1) return BigInteger.ONE;
        if (memo.containsKey(n)) return memo.get(n);
        BigInteger resultado = BigInteger.valueOf(n).multiply(fatorialTopDown(n - 1, memo));
        memo.put(n, resultado);
        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();

        System.out.println("\nFatorial Recursivo: " + fatorialRecursivo(numero));
        System.out.println("Fatorial Iterativo: " + fatorialIterativo(numero));
        System.out.println("Fatorial Bottom-Up: " + fatorialBottomUp(numero));
        System.out.println("Fatorial Top-Down: " + fatorialTopDown(numero, new HashMap<>()));
    }
} 