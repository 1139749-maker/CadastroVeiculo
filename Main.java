import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Veiculo> veiculos = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
        IO.println("""
            
        ─────────────────────────────────────────   
        CADASTRO DE VEÍCULOS
        ─────────────────────────────────────────
        1. Cadastrar Veículo
        2. Listar Veículos
        3. Consultar Veículo
        0. Sair
        ───────────────────────────────────────── """);

            opcao = Input.readInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> listarVeiculos();
                case 3 -> consultarVeiculo();
                case 0 -> IO.println("Sistema encerrado.");
                default -> IO.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void cadastrarVeiculo() {
        IO.println("""

        ─────────────────────────────────────────
        CADASTRAR VEÍCULO
        ───────────────────────────────────────── """);
        String marca = lerTextoObrigatorio("Informe a marca: ");
        String modelo = lerTextoObrigatorio("Informe o modelo: ");
        int ano = lerAno();
        String placa = lerTextoObrigatorio("Informe a placa: ");

        if (buscarPorPlaca(placa) != null) {
           IO.println("""

        >>> ERRO: Placa já cadastrada no sistema. <<<
        ───────────────────────────────────────── """);
            return;
        }

        veiculos.add(new Veiculo(marca, modelo, ano, placa));
        IO.println("""
        ─────────────────────────────────────────
        Veículo cadastrado com sucesso! """);
}

    private static void listarVeiculos() {
        IO.println("""
            
        ─────────────────────────────────────────    
        VEÍCULOS CADASTRADOS
        ───────────────────────────────────────── """);

        if (veiculos.isEmpty()) {
            IO.println("Nenhum veículo cadastrado.");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            IO.println((i + 1) + " - " + veiculos.get(i));
        }
    }

    private static void consultarVeiculo() {
        IO.println("""
            
        ─────────────────────────────────────────    
        CONSULTA DE VEÍCULO
        ─────────────────────────────────────────
        """);
        String placa = lerTextoObrigatorio("Informe a placa: ");
        Veiculo veiculo = buscarPorPlaca(placa);

        if (veiculo == null) {
            IO.println("Nenhum veículo encontrado com essa placa.");
        } else {
            IO.println("""
        ─────────────────────────────────────────
        Veículo encontrado!
        """);
            IO.println(veiculo.toString());
        }
    }

    private static Veiculo buscarPorPlaca(String placa) {
        String placaNormalizada = Veiculo.normalizarPlaca(placa);

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placaNormalizada)) {
                return veiculo;
            }
        }

        return null;
    }

    private static int lerAno() {
        int anoAtual = LocalDate.now().getYear();
        int anoMaximo = anoAtual + 1;

        while (true) {
            int ano = Input.readInt("Informe o ano de fabricação (1900 a " + anoMaximo + "): ");

            if (ano >= 1900 && ano <= anoMaximo) {
                return ano;
            }

IO.println("\n>>> ERRO: Ano inválido! Informe um ano entre 1900 e " + anoMaximo + ".\n");

        }
    }

    private static String lerTextoObrigatorio(String mensagem) {
        while (true) {
            String texto = IO.readln(mensagem).trim();

            if (!texto.isBlank()) {
                return texto;
            }

            IO.println("""
            A informação não pode ficar vazia.
            """);
        }
    }
}
