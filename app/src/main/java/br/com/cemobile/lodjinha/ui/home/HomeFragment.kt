package br.com.cemobile.lodjinha.ui.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DividerItemDecoration.VERTICAL
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.cemobile.domain.model.*
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.databinding.FragmentHomeBinding
import br.com.cemobile.lodjinha.ui.base.BaseFragment
import br.com.cemobile.lodjinha.ui.home.adapters.BestSellingProductsAdapter
import br.com.cemobile.lodjinha.ui.home.adapters.CategoriesAdapter
import br.com.cemobile.lodjinha.ui.product.detail.ProductDetailsActivity
import br.com.cemobile.lodjinha.ui.product.list.CategoryProductsListActivity
import br.com.cemobile.lodjinha.util.ScrollDirection
import br.com.cemobile.lodjinha.util.StartSnapHelper
import br.com.cemobile.lodjinha.util.autoCleared
import br.com.cemobile.lodjinha.util.image.ImageDownloader
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private val imageDownloader: ImageDownloader by inject()
    private var binding by autoCleared<FragmentHomeBinding>()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var bestSellingProductsAdapter: BestSellingProductsAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel

        setupViews()
        setupViewModel()
        bindObservers()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.loadHomeDataInformation()
    }

    override fun getTagName(): String = HomeFragment::class.java.simpleName

    /**
     * Responsible to setup required views
     */
    private fun setupViews() {
        setupToolbar()
        setupCarouselImageClick()
        setupCategoriesRecycler()
        setupProductsRecycler()
    }

    private fun setupToolbar() {
        activity?.let {
            val homeActivity = it as HomeActivity
            homeActivity.setTitle(getString(R.string.app_name))
            homeActivity.showToolbarLogo()
        }
    }

    /**
     * Handle carousel image click
     */
    @Suppress("UNCHECKED_CAST")
    private fun setupCarouselImageClick() {
        binding.carouselView.setImageClickListener { position ->
            val banners = carouselView.tag as List<Banner>
            val url = banners[position].linkUrl
            navigateTo(url)
        }
    }

    /**
     * Responsible to setup categories' recycler view
     */
    private fun setupCategoriesRecycler() {
        categoriesAdapter = CategoriesAdapter(::showProductsByCategory)
        binding.categoriesRecycler.adapter = categoriesAdapter
        binding.categoriesRecycler.setHasFixedSize(true)

        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(binding.categoriesRecycler)
    }

    /**
     * Responsible to setup products' recycler view
     */
    private fun setupProductsRecycler() {
        bestSellingProductsAdapter = BestSellingProductsAdapter(::showProductDetails)
        binding.productsRecycler.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, VERTICAL))
            adapter = bestSellingProductsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    bestSellingProductsAdapter.scrollDirection =
                        if (dy > 0) ScrollDirection.DOWN else ScrollDirection.UP
                }
            })
        }
    }

    /**
     * Open a browser and go to the [url] specified
     */
    private fun navigateTo(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    /**
     * Display product details information
     */
    private fun showProductDetails(view: View, product: Product) {
        activity?.let {
            val productImage = view.findViewById<ImageView>(R.id.productImage)
            val imagePair = Pair.create(productImage as View, "tImage")

            val productName = view.findViewById<TextView>(R.id.productNameText)
            val namePair = Pair.create(productName as View, "tName")

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(it, imagePair, namePair)
            val intent = ProductDetailsActivity.launchIntent(it, product)
            startActivity(intent, options.toBundle())
        }
    }

    private fun setupViewModel() {
        lifecycle.addObserver(viewModel)
    }

    private fun bindObservers() {
        // connect observers with related live data
        viewModel.getResourceLiveData().observe(this, Observer { resources ->
            when (resources?.status) {
                Status.SUCCESS -> showHomeData(resources.data)
                else -> Timber.e("Status inválido!")
            }

            binding.resource = resources
        })
    }

    private fun showHomeData(homeData: HomeData?) {
        homeData?.let {
            showBanners(it.banners)
            showCategories(it.categories)
            showBestSellingProducts(it.bestSellingProducts)
        }
    }

    // Observer that handles to show banner view
    private fun showBanners(banners: List<Banner>) {
        carouselView.setImageListener { position, imageView ->
            // load each image to show in banner
            val url = banners[position].imageUrl
            imageDownloader.loadUrl(url, R.drawable.no_image, imageView)
        }
        carouselView.pageCount = banners.size
        carouselView.tag = banners
    }

    // Observer that handles to show categories
    private fun showCategories(categories: List<Category>) {
        if (categories.isNotEmpty()) {
            categoriesAdapter.submitList(categories)
        }
    }

    // Observer that handles to show best selling products
    private fun showBestSellingProducts(products: List<Product>) {
        if (products.isNotEmpty()) {
            bestSellingProductsAdapter.submitList(products)
        }
    }

    private fun showProductsByCategory(category: Category) {
        activity?.let {
            startActivity(
                CategoryProductsListActivity.launchIntent(
                    it,
                    category.id,
                    category.description
                )
            )
        }
    }

}