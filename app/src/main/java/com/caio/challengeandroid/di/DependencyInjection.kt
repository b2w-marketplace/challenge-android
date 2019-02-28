package com.caio.lodjinha.di

object DependencyInjection {

//    val homeModule = module(override = true) {
//        viewModel { HomeViewModel(get(), get(), get()) }
//        factory { BannerPagerAdapter(get()) }
//        factory { ProductsMoreSallersAdapter() }
//        factory { CategoriesAdapter() }
//    }
//
//    val productDetailModule = module(override = true){
//        viewModel { ProductDetailViewModel(get()) }
//    }
//
//    val repositoryModule = module {
//        single { ProductRepository(get()) }
//        single { CategoryRepository(get()) }
//        single { BannerRepository(get()) }
//    }
//
//    val networkModule = module(override = true) {
//        single {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(RemoteConstant.URL_BASE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(get())
//                .build()
//            retrofit.create(BannerREST::class.java)
//        }
//        single {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(RemoteConstant.URL_BASE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(get())
//                .build()
//            retrofit.create(ProductREST::class.java)
//        }
//        single {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(RemoteConstant.URL_BASE)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .client(get())
//                .build()
//            retrofit.create(CategoryREST::class.java)
//        }
//        single {
//            OkHttpClient
//                .Builder()
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .build()
//        }
//    }
}