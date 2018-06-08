package br.com.dao;

import java.util.List;

public interface IManipularCSV<T> {
	
    void inserirLinha(T entity) throws Exception;    

    List<T> ler() throws Exception;
    
    void excluirLinha(int id) throws Exception;
}
