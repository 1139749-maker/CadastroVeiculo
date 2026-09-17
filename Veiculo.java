public class Veiculo {
    private final String marca;
    private final String modelo;
    private final int ano;
    private final String placa;

    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = normalizarPlaca(placa);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public static String normalizarPlaca(String placa) {
        return placa.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Marca: " + marca
                + " | Modelo: " + modelo
                + " | Ano: " + ano
                + " | Placa: " + placa;
    }
}
