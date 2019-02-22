package br.com.b2wmarketplace.alodjinha.network;

/**
 * Responsável pelo retorno do WebService
 * @param <String> tipo da variavel de retorno do WebService
 */
public interface AsyncTaskCompleteListener<String> {
    void onTaskComplete(String result);
}
