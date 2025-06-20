public class Endereco {
    private String bairro;
    private String rua;
    private int numero_casa;

    Endereco(){
        this.bairro = "";
        this.rua = "";
        this.numero_casa = 0;
    }

    Endereco(String bairro, String rua, int numero_casa){
        this.bairro = bairro;
        this.rua = rua;
        this.numero_casa = numero_casa;
    }

    public void setEndereco(String bairro, String rua, int numero_casa){
        this.bairro = bairro;
        this.rua = rua;
        this.numero_casa = numero_casa;
    }

    @Override
    public String toString(){
        return "\n\n|Bairro: "+bairro+"\n|Rua: "+rua+"\n|Numero da casa: "+numero_casa+"\n----------------------";
    }
}
