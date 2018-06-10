package br.com.wcisang.alojinha.Mocks;

import br.com.wcisang.alojinha.model.Category;

/**
 * Created by WCisang on 10/06/2018.
 */
public class CategoryMock {

    public static Category getGamesCategory(){
        Category category = new Category();
        category.setDescricao("Games");
        category.setId(1);
        category.setUrlImagem("http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png");
        return category;
    }
}
