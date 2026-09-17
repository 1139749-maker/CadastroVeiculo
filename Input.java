public class Input {
    public static int readInt(String message) {
        while (true) {
            try {
                String valor = IO.readln(message);
                return Integer.parseInt(valor.trim());
            } catch (Exception e) {
                IO.println("""
                    
        >>> ERRO: Valor inválido! Digite apenas números inteiros.
        """);
            }
        }
    }
}
