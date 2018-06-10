package br.com.wcisang.alojinha.Mocks;

import br.com.wcisang.alojinha.model.Product;

/**
 * Created by WCisang on 10/06/2018.
 */
public class ProductMock {

    public static Product getFullProduct(){
        Product product = new Product();
        product.setDescricao("Descrição do Produto");
        product.setId(1);
        product.setPrecoDe(1000);
        product.setPrecoPor(99);
        product.setNome("Produto");
        product.setUrlImagem("http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png");
        product.setCategoria(CategoryMock.getGamesCategory());
        return product;
    }
}
