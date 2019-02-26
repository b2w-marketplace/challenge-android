package marcus.com.br.b2wtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import marcus.com.br.b2wtest.ui.categoryproduct.CategoryProductActivity
import marcus.com.br.b2wtest.ui.main.MainActivity
import marcus.com.br.b2wtest.ui.productdetail.ProductDetailActivity

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesProductDetailActivity(): ProductDetailActivity

    @ContributesAndroidInjector
    abstract fun  contributesCategoryProductActivity(): CategoryProductActivity
}