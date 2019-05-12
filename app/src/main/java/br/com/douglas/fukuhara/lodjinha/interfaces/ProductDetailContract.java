package br.com.douglas.fukuhara.lodjinha.interfaces;

public interface ProductDetailContract {
    interface View {

        void populateProductLayout(String urlImagem, String nome, String precoDe, String precoPor, String descricao);
        void displayNoContentAvailable();
        void setFabEnabled(boolean enabled);
        void showReservationSuccess();
        void showGenericFailureOnReservation();
        void showFailureOnReservation(String serverMessage);
    }

    interface Presenter {
        void displayProductDetail();
        void onReserveButtonPressed();
        void onDialogConfirmationPressed();
        void disposeAll();
    }
}
