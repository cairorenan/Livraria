import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Clientes {
    private String nome;
    private String CPF;
    private Endereco endereco;
    private String devendo;
    private int compras;
    private Livros livro_pendente;
    private LocalDate data;

    Clientes(){
        this.nome = "";
        this.CPF = "";
        this.endereco = null;
        this.devendo = "";
        this.compras = 0;
        this.livro_pendente = null;
        this.data = null;
    }

    Clientes(String nome, String CPF, Endereco endereco, String devendo, int compras, Livros livro_pendente, LocalDate data){
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.devendo = devendo;
        this.compras = compras;
        this.livro_pendente = livro_pendente;
        this.data = data;
    }

    public void setCliente(String nome, String CPF, Endereco endereco, String devendo, int compras, Livros livro_pendete, LocalDate data){
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
        this.devendo = devendo;
        this.compras = compras;
        this.livro_pendente = livro_pendete;
        this.data = data;
    }

    public String getCliente(){
        String dataform = "";
        if (data != null){
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataform = data.format(formato);
        }
        return devendo.equals("nao")
            ? "----------------------\n|Nome: " + nome + "\n|CPF: " + CPF + "|Livros adquiridos: "+compras+"\n\n|Livro pendente: " + devendo +"\n\n|Endereco: " + endereco + "\n"
            : "----------------------\n|Nome: " + nome + "\n|CPF: " + CPF + "|Livros adquiridos: "+compras+"\n\n|Livro pendente: " + devendo +"\n\n|"+livro_pendente+"|"+dataform+" \n\n|Endereco: " + endereco + "\n";
    }

    public String getNome(){
        return nome;
    }

    public int getCompras(){
        return compras;
    }

    public void setCompras(int compras){
        this.compras = compras;
    }

    public Livros getLivro_Pendente(){
        return livro_pendente;
    }

    public void setLivro_Pendente(Livros livro_pendente){
        this.livro_pendente = livro_pendente;
    }

    public String getDevendo(){
        return devendo;
    }

    public void setDevendo(String devendo){
        this.devendo = devendo;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public LocalDate getData(){
        return data;
    }

}
