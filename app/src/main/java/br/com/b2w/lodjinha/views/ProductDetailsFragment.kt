package br.com.b2w.lodjinha.views

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.b2w.lodjinha.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_product_details.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductDetailsFragment : BaseFragment() {

    private val viewModel: ProductDetailsViewModel by viewModel()
    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_product_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveProduct()
        observerSaveProductResponse()
        getProduct()
    }

    private fun setupSaveProduct() {
        saveProductFab.setOnClickListener {
            saveProductProgressBar.show()
            saveProductFab.isClickable = false
            launch { viewModel.saveProduct(args.productId) }
        }
    }

    private fun observerSaveProductResponse() {
        viewModel.saveProductLiveData.observe(this@ProductDetailsFragment,  Observer { state ->
            saveProductProgressBar.hide()
            saveProductFab.isClickable = true
            val message = when (state) {
                is ProductDetailsViewModel.SaveProductState.SuccessState -> getString(R.string.save_product_successfully)
                is ProductDetailsViewModel.SaveProductState.ErrorState -> state.message
            }
            showDialog(message)
        })
    }

    private fun showDialog(message: String) =
        AlertDialog
            .Builder(requireContext())
            .setMessage(if (message.isEmpty()) context?.getString(R.string.save_product_error) else message)
            .setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
            .show()

    private fun getProduct() {
        launch { viewModel.getProduct(args.productId) }
        viewModel.productLiveData.observe(this@ProductDetailsFragment, Observer { product ->
            Picasso.get()
                .load(product.urlImagem)
                .into(productImageView)
            productNameTextView.text = product.name
            productOldPriceTextView.text = product.oldPrice.toString()
            productNewPriceTextView.text = product.newPrice.toString()
            bestSellerProductsTitleTextView.text = product.description.substringBefore(" ")
            productDescriptionTextView.text = getFormattedHtmlText(product.description)
        })
    }

    private fun getFormattedHtmlText(text: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
}