package trab1;


import java.util.ArrayDeque;

import java.util.Deque;

/**

Esta classe representa um inteiro não-negativo até 50 digítos.


public class CryptoInt {
// constante para o tamanho máximo de dígitos permitidos
private static final int MAX_SIZE = 50;
// deque para armazenar os dígitos do inteiro
private Deque<Integer> digits;


/**

Construtor por defeito. Inicializa um inteiro com o valor zero.
*/
public CryptoInt() {
digits = new ArrayDeque<>();
digits.addLast(0);


} public CryptoInt(Integer[] digitos) {
digits = new ArrayDeque<>();
for (Integer d : digitos) {
digits.addLast(d);
}
}
public CryptoInt(String grandeString) {
digits = new ArrayDeque<>();
for (int i = grandeString.length() - 1; i >= 0; i--) {
int d = Character.getNumericValue(grandeString.charAt(i));
digits.addFirst(d);
}
}
public CryptoInt(CryptoInt grande) {
digits = new ArrayDeque<>(grande.digits);
} 
/** Define o valor do número CryptoInt com base num dado array de inteiros.

Pode originar um erro se o número for muito grande.
@param digitos Um array de inteiros que representa
os dígitos CryptoInt
*/
public final void setCryptoInt(Integer[] digitos) {
digits.clear();
if (digitos.length > MAX_SIZE) {
throw new ArithmeticException("Número muito grande!");
}
for (Integer d : digitos) {
digits.addLast(d);
}
}
/** Define o valor do número CryptoInt com base numa string de números.

Pode originar um erro se o número for muito grande, ou
NumberFormatException se a string for mal formatada.
@param grandeString A string a converter em CryptoInt.
@throws NumberFormatException se a string estiver mal formatada.
*/
public final void setCryptoInt(String grandeString) throws NumberFormatException {
digits.clear();
if (grandeString.length() > MAX_SIZE) {
throw new ArithmeticException("Número muito grande!");
}
for (int i = grandeString.length() - 1; i >= 0; i--) {
int d = Character.getNumericValue(grandeString.charAt(i));
if (d < 0 || d > 9) {
throw new NumberFormatException("String mal formada!");
}
digits.addFirst(d);
}
} 
@Override
public String toString() {
StringBuilder sb = new StringBuilder();
for (Integer d : digits) {
sb.append(d);
}
return sb.toString();
}/** Convete CryptoInt num array de Inteiros.
*

@return Um array representando CryptoInt.
*/
public Integer[] toArray() {
Integer[] digitos = new Integer[digits.size()];
int i = 0;
for (Integer d : digits) {
digitos[i++] = d;
}
return digitos;
}/** Adiciona dois inteiros grandes sem alterar nenhum dos dois.
*

@param h um inteiro grande a adicionar a CryptoInt.

@return Um inteiro grande que é a soma dos dois inteiros.
*/
public CryptoInt addCryptoInt(CryptoInt h) {
Deque<Integer> sum = new ArrayDeque<>();
Deque<Integer> a = new ArrayDeque<>(this.digits);
Deque<Integer> b = new ArrayDeque<>(h.digits);

int carry = 0;
while (!a.isEmpty() || !b.isEmpty() || carry != 0) {
int d1 = a.isEmpty() ? 0 : a.removeLast();
int d2 = b.isEmpty() ? 0 : b.removeLast();
int d = d1 + d2 + carry;
sum.addFirst(d % 10);
carry = d / 10;
}

if (sum.size() > MAX_SIZE) {
throw new ArithmeticException("Número muito grande!");
}

return new CryptoInt((CryptoInt) sum);
} 

/** Multiplica outro inteiro grande ao CryptoInt sem alterar nenhum dos dois.
 *
 * @param h um inteiro grande a adicionar a CryptoInt.
 * @return Um inteiro grande que é o produto dos dois inteiros.
 */
public CryptoInt multiplicaCryptoInt(CryptoInt h) {
    Deque<Integer> resultado = new ArrayDeque<>();
    Deque<Integer> a = new ArrayDeque<>(this.digits);
    Deque<Integer> b = new ArrayDeque<>(h.digits);

    // preenche o array de resultado com zeros
    for (int i = 0; i < a.size() + b.size(); i++) {
        resultado.offerLast(0);
    }
    // multiplica cada dígito do primeiro inteiro pelo segundo
   for (int i = 0; i < a.size(); i++) {
for (int j = 0; j < b.size(); j++) {
resultado.offerFirst(resultado.pollFirst() + a.pollFirst() * b.pollFirst());
resultado.offerFirst(resultado.pollFirst() + resultado.pollFirst() / 10);
resultado.offerFirst(resultado.pollFirst() % 10);
}
}

    // remove os zeros à direita
    while (resultado.size() > 1 && resultado.peekLast() == 0) {
        resultado.pollLast();
    }

    if (resultado.size() > MAX_SIZE) {
        throw new ArithmeticException("Número muito grande!");
    }

    return new CryptoInt((CryptoInt) resultado);
} 
// Aumenta o array de inteiros um dígito extra.
private Integer[] aumenta(Integer[] a) {
    Integer[] b = new Integer[a.length + 1];
    System.arraycopy(a, 0, b, 1, a.length);
    return b;
}
public static CryptoInt getCryptoInt(String s) {
  // Converte a String s em um array de inteiros
  Integer[] digitos = new Integer[s.length()];
  for (int i = 0; i < s.length(); i++) {
    digitos[i] = Integer.valueOf(s.substring(i, i+1));
  }

  // Cria um novo CryptoInt a partir do array de inteiros
  CryptoInt num = new CryptoInt(digitos);

  // Multiplica o novo CryptoInt por dois e retorna o resultado
  return num.multiplicaCryptoInt(new CryptoInt(new Integer[]{2}));
}
public boolean isZero() {
  
    return this.digits.isEmpty();
}
}
