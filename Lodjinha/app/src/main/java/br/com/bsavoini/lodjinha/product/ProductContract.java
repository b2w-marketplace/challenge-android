package br.com.bsavoini.lodjinha.product;

import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.api.model.ReservationResponse;

public interface ProductContract {
    interface ProductInteractor {
        void requestProductReservation(int productId, ProductReservationRequestListener listener);

        interface ProductReservationRequestListener {
            void onProductReservationRequestSuccessful(ReservationResponse reservationResponse);
            void onProductReservationFail();
        }
    }

    interface ProductView {
        void enableToolbarBackButton();

        ProductModel retrieveProductModel();

        void bindTollbarTitleWithProduct(String name);
        void bindProductImageView(String imageUrl);

        void bindProductNameTextView(String name);
        void bindOriginalPriceTextView(String price);
        void bindCurrentPriceTextView(String price);

        void bindProductDescriptionTextView(String description);

        void initFab(int productId);
        void disableFabClick();
        void enableFabClick();

        void showSucessDialog(String message);
        void showErrorDialog(String message);

        void showProgressBar();
        void hideProgressBar();
    }

    interface ProductPresenter {
        void initViews();
        void requestProductReservation(int productId);
    }
}
