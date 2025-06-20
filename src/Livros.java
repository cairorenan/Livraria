public class Livros {
    private String nome;
    private String genero;
    private int unidade;

    Livros(){
        this.nome = "";
        this.genero = "";
        this.unidade = 1;
    }

    Livros(String nome, String genero, int unidade){
        this.nome = nome;
        this.genero = genero;
        this.unidade = unidade;
    }

    public void setDados(String nome, String genero, int unidade){
        this.nome = nome;
        this.genero = genero;
        this.unidade = unidade;
    }

    public String getDados(){
        return "---------------------\n|Nome: "+nome+"\n|Gênero: "+genero+"\n|Unidades: "+unidade+"\n";
    }

    public String getNome(){
        return nome;
    }

    public void setUnidade(int unidade){
        this.unidade = unidade;
    }

    public int getUnidade(){
        return unidade;
    }
    
    @Override
    public String toString(){
        return getDados();
    }

    public String getGenero(){
        return genero;
    }

}
