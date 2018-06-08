package br.com.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.bean.Usuario;

public class AgendaDAO implements IManipularCSV<Usuario> {

    private static final String caminhoCSV = "\\WebContent\\agenda.csv"; ///Caminho do arquivo na máquina local
    private static final String separador = ";";
    private static List<Usuario> contatos;
    private static Usuario contato;
    private static BufferedReader bufferedReader;
    private static PrintWriter gravarArq; 
	       
	@Override
	public void inserirLinha(Usuario entity) throws Exception {
		contatos = this.ler();		        
        if(entity.getId() == 0) {
            int pos = contatos.size() - 1;
            entity.setId(contatos.get(pos).getId() + 1);
        }
        contatos.add(entity);
        try (FileWriter arq = new FileWriter(caminhoCSV)) {
            gravarArq = new PrintWriter(arq);
            for(Usuario a: contatos){
                gravarArq.printf(String.format("%d;%s;%s;%s;%s\n", a.getId(), a.getNome(), a.getEmail(), a.getTelefone(), a.getSenha()));
            }
        }
		
	}

	@Override
	public List<Usuario> ler() throws Exception {
		 File file = new File(caminhoCSV);
	        FileReader fileReader = new FileReader(file);
	        bufferedReader = new BufferedReader(fileReader);
	        String line;
	        String[] record;
	        contatos = new ArrayList<>();

	        while ((line = bufferedReader.readLine()) != null) {
	            record = line.split(separador);
	            contato = new Usuario();
	            contato.setId(Integer.parseInt(record[0]));
	            contato.setNome(record[1]);
	            contato.setEmail(record[2]);
	            contato.setTelefone(record[3]);
	            contato.setSenha(record[4]);
	            contatos.add(contato);
	        }
	        return contatos;
	}

	@Override
	public void excluirLinha(int id) throws Exception {
		contatos = this.ler();
		try (FileWriter arq = new FileWriter(caminhoCSV)) {
            gravarArq = new PrintWriter(arq);
            for(Usuario a: contatos){
            	if(a.getId() != id) {
            		gravarArq.printf(String.format("%d;%s;%s;%s;%s\n", a.getId(), a.getNome(), a.getEmail(), a.getTelefone(), a.getSenha()));
            	}
            }
        }             
	}
}
