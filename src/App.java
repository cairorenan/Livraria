import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
public class App {
    public static ArrayList<Livros> Livros_Lista = new ArrayList<>();
    public static ArrayList<Clientes> Clientes_Lista = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Clientes_Lista = Dados.carregarClientes();
        Livros_Lista = Dados.carregarLivros();
        int operacao = 0;
        do{
            System.out.println("------Menu------");
            System.out.println("1 - Livros");
            System.out.println("2 - Clientes");
            System.out.println("3 - Gestão");
            System.out.println("4 - Sair");
            operacao = scanner.nextInt();
            switch(operacao){
                case 1:
                    Livros();
                    break;
                case 2:
                    Clientes();
                    break;
                case 3:
                    Gestao();
            }
        }while(operacao!=4);
    }

    public static void Livros(){
        scanner.nextLine();
        int operacao = 0;
        do{
            System.out.println("\n------Livros------");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Buscar Livro");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Editar");
            System.out.println("5 - Voltar");
            operacao = scanner.nextInt();
            switch (operacao){
                case 1:
                    Cadastrar_Livro();
                    Dados.salvarLivros(Livros_Lista);
                    break;
                case 2:
                    Livros Index = Buscar_Livro();
                    if (Index != null){
                        System.out.println(Index.getDados());
                    }else{
                        System.out.println("Livro não encontrado!");
                    }
                    break;
                case 3:
                    Listar_Livros();
                    break;
                case 4:
                    Editar_Livro();
                    break;
            }
        }while (operacao!=5);

    }

    public static void Clientes(){
        scanner.nextLine();
        int operacao = 0;
        do{
            System.out.println("\n------Clientes------");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Buscar Cliente");
            System.out.println("3 - Listar Clientes");
            System.out.println("4 - Editar");
            System.out.println("5 - Voltar");
            operacao = scanner.nextInt();
            switch(operacao){
                case 1:
                    Cadastrar_Cliente();
                    Dados.salvarClientes(Clientes_Lista);
                    break;
                case 2:
                    Clientes Index = Buscar_Cliente(true);
                    if(Index != null){
                        System.out.println(Index.getCliente());
                    }else{
                        System.out.print("Cliente não encontrado!");
                    }
                    break;
                case 3:
                    Listar_CLientes();
                    break;
                case 4 :
                    Editar_Cliente();
                    break;
                
            }
        }while (operacao!=5);
    }

    public static void Cadastrar_Livro(){
        scanner.nextLine();
        System.out.print("Informe o nome do livro: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o gênero: ");
        String genero =  scanner.nextLine();
        System.out.print("Informe quantas unidades estão disponiveis: ");
        int unidade = scanner.nextInt();
        Livros livros = new Livros(nome, genero, unidade);
        Livros_Lista.add(livros);
    }

    public static Livros Buscar_Livro(){
        scanner.nextLine();
        System.out.print("Informe o nome do livro: ");
        String nome_busca = scanner.nextLine();
        for (Livros l : Livros_Lista){
            if(l.getNome().equals(nome_busca)){
                return l;
            }
        }return null;
    }

    public static void Listar_Livros(){
        for (Livros l : Livros_Lista){
            System.out.println(l.getDados());
        }
    }

    public static void Editar_Livro(){
        int operacao = 0;
        scanner.nextLine();
        do{
            System.out.println("------Editar------");
            System.out.println("1 - Apagar Livro");
            System.out.println("2 - Editar Livro");
            System.out.println("3 - Voltar");
            operacao = scanner.nextInt();
            switch (operacao){
                case 1:
                    Apagar_Livro();
                    Dados.salvarLivros(Livros_Lista);
                    break;
                case 2:
                    Editar_Dados_Livro();
                    Dados.salvarLivros(Livros_Lista);
                    break;
            }
        }while(operacao!=3);
    }

    public static void Apagar_Livro(){
        Livros Index = Buscar_Livro();
        if(Index != null){
            Livros_Lista.remove(Index);
        }else{
            System.out.println("Livro não encontrado!");
        }
    }

    public static void Editar_Dados_Livro(){
        Livros Index = Buscar_Livro();
        if (Index != null){
            System.out.print("Informe o novo nome do livro: ");
            String nome = scanner.nextLine();
            System.out.print("Informe o novo gênero do livro: ");
            String genero = scanner.nextLine();
            System.out.print("Informe a nova quantia de unidades do livr: ");
            int unidade = scanner.nextInt();
            Index.setDados(nome, genero, unidade);
        }else{
            System.out.println("Livro não encontrado!");
        }
    }

    public static void Cadastrar_Cliente(){
        scanner.nextLine();
        System.out.print("Informe o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Informe o cpf do cliente: ");
        String CPF = scanner.nextLine();
        System.out.print("Informe o bairro do cliente: ");
        String bairro = scanner.nextLine();
        System.out.print("Informe a rua do cliente: ");
        String rua = scanner.nextLine();
        System.out.print("Informe o numero da casa: ");
        int numero_casa = scanner.nextInt();
        Endereco endereco = new Endereco(bairro, rua, numero_casa);
        Clientes clientes = new Clientes(nome, CPF, endereco, "nao", 0, null, null);
        Clientes_Lista.add(clientes);
    }

    public static Clientes Buscar_Cliente(boolean limpar_buffer){
        if (limpar_buffer){
            scanner.nextLine();
        }
        System.out.print("Informe o nome do cliente: ");
        String nome_busca = scanner.nextLine();
        for (Clientes c : Clientes_Lista){
            if (c.getNome().equals(nome_busca)){
                return c;
            }
        }return null;

    }

    public static void Listar_CLientes(){
        for (Clientes c : Clientes_Lista){
            System.out.println(c.getCliente());
        }
    }

    public static void Editar_Cliente(){
        scanner.nextLine();
        int operacao = 0;
        System.out.println("------Editar------");
        System.out.println("1 - Apagar Cliente");
        System.out.println("2 - Editar Cliente");
        System.out.println("3 - Voltar");
        operacao = scanner.nextInt();
        switch(operacao){
            case 1:
                Apagar_Cliente();
                Dados.salvarClientes(Clientes_Lista);
                break;
            case 2:
                Editar_Dados_Cliente();
                Dados.salvarClientes(Clientes_Lista);
                break;
        }
        
    }

    public static void Apagar_Cliente(){
        scanner.nextLine();
        Clientes Index = Buscar_Cliente(true);
        if(Index!=null){
            Clientes_Lista.remove(Index);
        }else{
            System.out.println("Cliente não encontrado!");
        }
    }

    public static void Editar_Dados_Cliente(){
        Clientes Index = Buscar_Cliente(true);
        if(Index!=null){
            System.out.print("Informe o novo nome do cliente: ");
            String nome = scanner.nextLine();
            System.out.print("Informe o novo cpf do cleinte: ");
            String CPF = scanner.nextLine();
            System.out.print("Informe o novo bairro do cliente: ");
            String bairro = scanner.nextLine();
            System.out.print("Informe a nova rua: ");
            String rua = scanner.nextLine();
            System.out.print("Informe o novo numero de casa: ");
            int numero_casa = scanner.nextInt();
            Endereco endereco = new Endereco(bairro, rua, numero_casa);
            int compras = Index.getCompras();
            Livros livro_pendente = Index.getLivro_Pendente();
            LocalDate data = Index.getData();
            String devendo = Index.getDevendo();
            Index.setCliente(nome, CPF, endereco, devendo, compras, livro_pendente, data);
        }else{
            System.out.println("Cliente não encontrado!");
        }
    }

    public static void Gestao(){
        scanner.nextLine();
        int operacao = 0;
        do{
            System.out.println("------Gestão------");
            System.out.println("1 - Vender Livro");
            System.out.println("2 - Alugar Livro");
            System.out.println("3 - Devolução");
            System.out.println("4 - Voltar");
            operacao = scanner.nextInt();
            switch(operacao){
                case 1:
                    Vender_Livro();
                    Dados.salvarLivros(Livros_Lista);
                    Dados.salvarClientes(Clientes_Lista);
                    break;
                case 2:
                    Alugar_Livro();
                    Dados.salvarLivros(Livros_Lista);
                    Dados.salvarClientes(Clientes_Lista);
                    break;
                case 3:
                    Devolucao();
                    Dados.salvarLivros(Livros_Lista);
                    Dados.salvarClientes(Clientes_Lista);
                    break;
            }
        }while(operacao!=4);
    }

    public static void Vender_Livro(){
        Livros Index_L = Buscar_Livro();
        Clientes Index_C = Buscar_Cliente(false);
        if (Index_L != null && Index_C != null){
            int unidade = Index_L.getUnidade();
            int compras = Index_C.getCompras();
            unidade-=1;
            compras+=1;
            Index_L.setUnidade(unidade);
            Index_C.setCompras(compras);
            if (unidade == 0){
                Livros_Lista.remove(Index_L);
            }
        }else{
            if (Index_L == null && Index_C != null){
                System.out.println("Livro não encontrado!");
            }else if (Index_C == null && Index_L !=null){
                System.out.println("Cliente não encontrado!");
            }else{
                System.out.println("Livro e Cliente não encontrado!");
            }
        }
    }

    public static void Alugar_Livro(){
        Livros Index_L = Buscar_Livro();
        Clientes Index_C = Buscar_Cliente(false);
        if (Index_L != null && Index_C != null){
            int unidade = Index_L.getUnidade();
            if ("nao".equals(Index_C.getDevendo())){
                unidade -= 1;
                Livros copia = new Livros(Index_L.getNome(), Index_L.getGenero(), 1);
                Index_L.setUnidade(unidade);
                Index_C.setLivro_Pendente(copia);
                Index_C.setDevendo("sim");
                LocalDate data = LocalDate.now();
                Index_C.setData(data);
                if (unidade == 0){
                    Livros_Lista.remove(Index_L);
                }
            }else{
                System.out.println("O cliente já possui um livro pendente");
            }
        }else{
            if (Index_L == null && Index_C != null){
                System.out.println("Livro não encontrado!");
            }else if (Index_C == null && Index_L !=null){
                System.out.println("Cliente não encontrado!");
            }else{
                System.out.println("Livro e Cliente não encontrado!");
            }
        }
        
    }

    public static void Devolucao(){
        Clientes Index_C = Buscar_Cliente(true);
        if (Index_C!=null){
            Livros Livro_pendente = Index_C.getLivro_Pendente();
            String nome_livro = Livro_pendente.getNome();
            boolean encontrado = false;
            for (Livros l : Livros_Lista){
                if(nome_livro.equals(l.getNome())){
                    int unidade = l.getUnidade();
                    l.setUnidade(unidade+1);
                    Livros_Lista.remove(Livro_pendente);
                    Index_C.setDevendo("nao");
                    encontrado = true;
                    break;
                }
            }
            if(!encontrado){
                Livros livro_devolucao = new Livros(Livro_pendente.getNome(),Livro_pendente.getGenero(), Livro_pendente.getUnidade());
                Livros_Lista.add(livro_devolucao);
            }
            Index_C.setDevendo("nao");
            Index_C.setLivro_Pendente(null);
        }
    }
    
}