package br.com.douglas.fukuhara.lodjinha.interfaces;

public interface ProductDetailContract {
    interface View {

        void populateProductLayout(String urlImagem, String nome, String precoDe, String precoPor, String descricao);
        void displayNoContentAvailable();
    }

    interface Presenter {
        void displayProductDetail();
    }
}
