import java.util.Arrays;
import java.util.Comparator;

// Define a classe Item para armazenar peso e valor de cada item
class Item {
    int peso;
    int valor;

    // Construtor para inicializar peso e valor
    public Item(int peso, int valor) {
        this.peso = peso;
        this.valor = valor;
    }
}

public class ProblemaMochilaFracionaria {

    // Função para calcular o valor máximo que pode ser obtido
    // com capacidade W
    public static double obterValorMaximo(int capacidade, Item[] itens) {
        // Ordena os itens pela razão valor/peso em ordem decrescente
        Arrays.sort(itens, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double razao1 = (double) o1.valor / o1.peso;
                double razao2 = (double) o2.valor / o2.peso;
                // Ordena os itens com base na razão em ordem decrescente
                return Double.compare(razao2, razao1);
            }
        });

        double valorTotal = 0.0;  // Inicializa o valor total

        // Itera por todos os itens e adiciona a parte fracionária
        // de um item na mochila até que a mochila esteja cheia
        for (Item item : itens) {
            // Se adicionar o item inteiro não exceder a capacidade,
            // adiciona-o completamente e atualiza a capacidade
            if (capacidade - item.peso >= 0) {
                capacidade -= item.peso;
                valorTotal += item.valor;
            } else {
                // Se não for possível adicionar o item inteiro,
                // adiciona a parte fracionária dele
                double fracao = (double) capacidade / item.peso;
                valorTotal += item.valor * fracao;
                break;  // Não há mais espaço disponível na mochila
            }
        }

        return valorTotal;
    }

    public static void main(String[] args) {
        int capacidade = 50;  // Capacidade da mochila
        Item[] itens = {      // Array de itens
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        };

        // Obtém o valor máximo possível na mochila
        double valorMaximo = obterValorMaximo(capacidade, itens);
        System.out.println("Valor máximo na Mochila = " + valorMaximo);
    }
}