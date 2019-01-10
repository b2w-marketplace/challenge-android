package br.com.bsavoini.lodjinha.product;

import br.com.bsavoini.lodjinha.api.model.ProductModel;
import br.com.bsavoini.lodjinha.api.model.ReservationResponse;

public class ProductPresenterImpl implements ProductContract.ProductPresenter,
        ProductContract.ProductInteractor.ProductReservationRequestListener {

    private ProductContract.ProductView view;
    private ProductContract.ProductInteractor interactor;

    public ProductPresenterImpl(ProductContract.ProductView view, ProductContract.ProductInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void initViews() {
        view.enableToolbarBackButton();

        ProductModel productModel = view.retrieveProductModel();

        view.bindTollbarTitleWithProduct(productModel.getName());
        view.bindProductImageView(productModel.getImageURL());

        view.bindProductNameTextView(productModel.getName());
        view.bindOriginalPriceTextView("De: " + productModel.getOriginalPrice());
        view.bindCurrentPriceTextView("Por " + productModel.getCurrentPrice());

        view.bindProductDescriptionTextView(productModel.getDescription());
        view.initFab(productModel.getId());
    }

    @Override
    public void requestProductReservation(int productId) {
        view.showProgressBar();
        view.disableFabClick();
        interactor.requestProductReservation(productId, this);
    }

    @Override
    public void onProductReservationRequestSuccessful(ReservationResponse reservationResponse) {
        view.hideProgressBar();
        if (reservationResponse.getResult() != null && reservationResponse.getResult().equals("success")) {
            view.showSucessDialog("Produto reservado com sucesso");
        } else {
            view.showErrorDialog("Ops! Não foi possível reservar este produto");
        }
        view.enableFabClick();
    }

    @Override
    public void onProductReservationFail() {
        view.hideProgressBar();
        view.showErrorDialog("Ops! Não foi possível reservar este produto");
        view.enableFabClick();
    }
}
