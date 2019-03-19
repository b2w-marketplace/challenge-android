package com.danilodequeiroz.webapi

import com.danilodequeiroz.webapi.model.banner.Banner
import com.danilodequeiroz.webapi.model.banner.BannersPayload
import com.danilodequeiroz.webapi.model.category.ProductCategoriesPayload
import com.danilodequeiroz.webapi.model.category.ProductCategory
import com.danilodequeiroz.webapi.model.post.ReserveProductPayload
import com.danilodequeiroz.webapi.model.product.BestSellingProductsPayload
import com.danilodequeiroz.webapi.model.product.Product
import com.danilodequeiroz.webapi.model.product.ProductsPayload

fun productPOJOmodel() =
    Product(1,  categoryPOJOModel(), "xbox", 2000, 1500.0, "lorem ispun", "localhost:8080")

fun bannersPOJOmodel(): BannersPayload {
    val linkUrl = "https://images-submarino.b2w.io/spacey/2017/02/03/sub-home-dest-full-655x328-touch-play.png"
    val banner = Banner(1, linkUrl, "localhost:8080")
    return BannersPayload(listOf(banner))
}

fun bestSellingsPOJOmodel() =
    BestSellingProductsPayload(listOf(productPOJOmodel()))

fun categoriesPOJOmodel() =
    ProductCategoriesPayload(listOf(categoryPOJOModel()))

fun productsListPOJOmodel() =
    ProductsPayload(0,listOf(productPOJOmodel()))



fun categoryPOJOModel() = ProductCategory(1,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Games")

val desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna"

val products = listOf(
    Product(1, categoryPOJOModel(), "Fifa 09 PS3 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(2, categoryPOJOModel(), "Fifa 10 PS3 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(3, categoryPOJOModel(), "Fifa 11 PS3 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(4, categoryPOJOModel(), "Fifa 12 PS3 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(5, categoryPOJOModel(), "Fifa 13 PS3 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(6, categoryPOJOModel(), "Fifa 14 PS4 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(7, categoryPOJOModel(), "Fifa 15 PS4 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(8, categoryPOJOModel(), "Fifa 16 PS4 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(9, categoryPOJOModel(), "Fifa 17 PS4 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(10, categoryPOJOModel(), "Fifa 18 PS4 - Mídia Física",199,132.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png"),
    Product(11, categoryPOJOModel(), "Fifa 19 PS4 - Mídia Física",279,172.9,desc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png")
)


fun categoryHomeMocks() = ProductCategoriesPayload(
        listOf(
            ProductCategory(1,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Games"),
            ProductCategory(2,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Livros"),
            ProductCategory(3,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Celulares"),
            ProductCategory(4,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Informática"),
            ProductCategory(5,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Eletrodomésticos"),
            ProductCategory(6,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","TVs"),
            ProductCategory(7,"https://www.gstatic.com/images/branding/googlemic/2x/googlemic_color_24dp.png","Papelaria")
            )
    )

fun productsListMocks() = ProductsPayload(0, products)
fun productsHomeMocks() = BestSellingProductsPayload(products)

val longDesc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Neque convallis a cras semper auctor neque vitae tempus quam. Donec ultrices tincidunt arcu non sodales neque sodales. Venenatis lectus magna fringilla urna porttitor rhoncus dolor purus. Quis hendrerit dolor magna eget est lorem ipsum dolor. Sed lectus vestibulum mattis ullamcorper. Tincidunt lobortis feugiat vivamus at augue eget arcu dictum. At in tellus integer feugiat scelerisque varius morbi. Aliquet eget sit amet tellus cras. Nec dui nunc mattis enim ut tellus elementum sagittis. Sed lectus vestibulum mattis ullamcorper velit sed. Tortor consequat id porta nibh venenatis. Suspendisse ultrices gravida dictum fusce ut placerat orci." +
        "Nunc congue nisi vitae suscipit tellus mauris. Arcu dictum varius duis at consectetur lorem. Vel pretium lectus quam id leo. In egestas erat imperdiet sed euismod nisi porta lorem. Dolor sed viverra ipsum nunc aliquet bibendum enim facilisis gravida. Commodo sed egestas egestas fringilla phasellus faucibus. Enim sit amet venenatis urna cursus eget. Id semper risus in hendrerit. Vitae proin sagittis nisl rhoncus mattis rhoncus. Porttitor eget dolor morbi non arcu. Tristique senectus et netus et malesuada. Egestas egestas fringilla phasellus faucibus scelerisque. Orci a scelerisque purus semper eget duis at."

val productDetail = Product(1, categoryPOJOModel(), "Fifa 19 PS4 - Mídia Física",279,172.9,longDesc,"https://ssl.gstatic.com/gb/images/a/17bdcddea9.png")
val reservePayload = ReserveProductPayload("sucess", "success")
