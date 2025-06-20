import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dados {
    private static final String CLIENTES = "data/clientes.json";
    private static final String LIVROS = "data/livros.json";
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
        
    public static void salvarClientes(List<Clientes> lista){
        try (FileWriter writer = new FileWriter(CLIENTES)){
            gson.toJson(lista, writer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void salvarLivros(List<Livros> lista){
        try (FileWriter writer = new FileWriter(LIVROS)){
            gson.toJson(lista, writer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Clientes> carregarClientes(){
        try (FileReader reader = new FileReader(CLIENTES)){
            Type tipoLista = new TypeToken<ArrayList<Clientes>>() {}.getType();
            ArrayList<Clientes> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        }catch (IOException e){
            return new ArrayList<>();
        }
    }

    public static ArrayList<Livros> carregarLivros(){
        try (FileReader reader = new FileReader(LIVROS)){
            Type tipolista = new TypeToken<ArrayList<Livros>>() {}.getType();
            ArrayList<Livros> lista = gson.fromJson(reader, tipolista);
            return lista != null ? lista : new ArrayList<>();
        }catch (IOException e){
            return new ArrayList<>();
        }
    }
}