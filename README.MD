# Calculadora de Fatorial em Java

Este projeto Java calcula o **fatorial de um número inteiro não-negativo**, utilizando diferentes abordagens, incluindo **recursão**, **iterativo** e **programação dinâmica** com `BigInteger`, para suportar valores grandes.

## 🧠 É possível calcular o fatorial de um número acima de 100 com recursão?

**Tecnicamente, sim.** Mas **não é recomendado**. Veja os motivos abaixo:

### ❌ Problemas da recursão para números grandes

* A recursão em Java utiliza a **pilha de execução (stack)** da JVM.
* Cada chamada recursiva adiciona um novo frame à pilha.
* A JVM possui um **limite de profundidade da pilha por thread**.
* Para números muito grandes (ex: `fatorial(10000)`), o programa pode lançar um:

  ```
  java.lang.StackOverflowError
  ```

### ✅ Por que usamos `BigInteger`?

* O fatorial cresce exponencialmente. Exemplo: `100!` tem **158 dígitos**!
* Tipos primitivos como `int` e `long` **estouram rapidamente**:

    * `20! = 2.432.902.008.176.640.000` → já ultrapassa o limite do tipo `long`.
* `BigInteger` é uma classe do Java que permite trabalhar com **números inteiros muito grandes**, sem limite de tamanho.

---

## ✅ Códigos para cálculo de fatorial

### ✅ Solução Recursiva

```java
public static BigInteger fatorial(int n) {
    if (n == 0 || n == 1)
        return BigInteger.ONE;
    return BigInteger.valueOf(n).multiply(fatorial(n - 1));
}
```

> ⚠️ Funciona bem até \~1000, dependendo da configuração da JVM.

---

### ✅ Solução Iterativa

```java
public static BigInteger fatorialIterativo(int n) {
    BigInteger resultado = BigInteger.ONE;
    for (int i = 2; i <= n; i++) {
        resultado = resultado.multiply(BigInteger.valueOf(i));
    }
    return resultado;
}
```

> ✔️ Evita estouro de pilha e é mais segura para grandes entradas.

---

### ✅ Solução com Programação Dinâmica

#### 🔁 Bottom-Up

```java
public static BigInteger fatorialBottomUp(int n) {
    BigInteger[] dp = new BigInteger[n + 1];
    dp[0] = BigInteger.ONE;
    for (int i = 1; i <= n; i++) {
        dp[i] = dp[i - 1].multiply(BigInteger.valueOf(i));
    }
    return dp[n];
}
```

#### 🔁 Top-Down (com memoização)

```java
import java.util.HashMap;

public static BigInteger fatorialTopDown(int n, HashMap<Integer, BigInteger> memo) {
    if (n == 0 || n == 1)
        return BigInteger.ONE;
    if (memo.containsKey(n))
        return memo.get(n);
    BigInteger resultado = BigInteger.valueOf(n).multiply(fatorialTopDown(n - 1, memo));
    memo.put(n, resultado);
    return resultado;
}
```

---

## 🔍 Diferença entre a solução 2 (Iterativa) e a solução 3 (Programação Dinâmica)

| Critério                | Iterativa                    | Programação Dinâmica         |
| ----------------------- | ---------------------------- | ---------------------------- |
| Abordagem               | Loop simples                 | Memoização ou tabela         |
| Uso de memória          | Constante                    | Usa array ou estrutura extra |
| Performance             | Muito eficiente              | Similar, mas mais escalável  |
| Reutilização de valores | Não reutiliza explicitamente | Reutiliza via cache (DP)     |

* Ambas evitam estouro de pilha.
* A programação dinâmica é útil se precisar reutilizar fatorial de `n` várias vezes.
* A abordagem bottom-up se comporta como a iterativa, mas guarda todos os resultados intermediários.

---

## 📌 Resumo

* ✅ A recursão funciona para valores pequenos e médios (até \~100–1000).
* ⚠️ Para valores maiores, use a **versão iterativa** ou **dinâmica**.
* ✅ Sempre use `BigInteger` para garantir que o resultado não ultrapasse os limites de tipos primitivos.
* ✅ Programação dinâmica permite **reutilização de resultados parciais**, sendo ideal para sistemas mais complexos.

---

## 💡 Como Executar

1. Compile o código:

```bash
javac Fatorial.java
```

2. Execute:

```bash
java Fatorial
```

3. Insira um número inteiro e veja o fatorial calculado.

---

## 🛠 Requisitos

* Java 8 ou superior (recomendado Java 17+)

---


