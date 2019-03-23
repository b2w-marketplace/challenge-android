package br.com.rbueno.lodjinha.ui.home.mostsold

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Product
import br.com.rbueno.lodjinha.model.ProductList
import br.com.rbueno.lodjinha.ui.product.list.ProductListAdapter
import com.google.gson.GsonBuilder
import dagger.android.support.AndroidSupportInjection

class MostSoldFragment : Fragment() {

    private val recyclerView by lazy { view?.findViewById<RecyclerView>(R.id.recycler_products) }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_most_sold, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
    }

    private fun configRecyclerView() {
        with(recyclerView) {
            this?.adapter = ProductListAdapter(createMock()) { navigateToProduct(it) }
            this?.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MostSoldFragment.context)
            this?.addItemDecoration(
                androidx.recyclerview.widget.DividerItemDecoration(
                    this@MostSoldFragment.context,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun navigateToProduct(product: Product) {
        findNavController().navigate(R.id.product_detail_dest)
    }

    private fun createMock(): ProductList {
        val mostSoldJson = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"nome\": \"Fifa 17\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/128926/4/128926443_1GG.png\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 11,\n" +
                "      \"nome\": \"Forza Horizon 3\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/128831/2/128831209_1GG.jpg\",\n" +
                "      \"descricao\": \"Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 16,\n" +
                "      \"nome\": \"Assassins Creed\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/129735/8/129735892_1GG.jpg\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 21,\n" +
                "      \"nome\": \"Mafia 3\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/128938/9/128938997_1GG.jpg\",\n" +
                "      \"descricao\": \"Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 28,\n" +
                "      \"nome\": \"Mortal Kombat XL\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/126077/6/126077695_1GG.jpg\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 31,\n" +
                "      \"nome\": \"GTA V\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/120857/6/120857615_1GG.png\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 33,\n" +
                "      \"nome\": \"Uncharted Collections\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/124494/3/124494399_1GG.png\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 40,\n" +
                "      \"nome\": \"Drive Club\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/120585/3/120585373_1GG.jpg\",\n" +
                "      \"descricao\": \"Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 26,\n" +
                "      \"nome\": \"Resident Evil\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/129958/8/129958851_1GG.jpg\",\n" +
                "      \"descricao\": \"Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 23,\n" +
                "      \"nome\": \"Batman VR\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/129345/1/129345173_1GG.jpg\",\n" +
                "      \"descricao\": \"Primeiro eu queria cumprimentar os internautas. -Oi Internautas! Depois dizer que o meio ambiente é sem dúvida nenhuma uma ameaça ao desenvolvimento sustentável. E isso significa que é uma ameaça pro futuro do nosso planeta e dos nossos países. O desemprego beira 20%, ou seja, 1 em cada 4 portugueses.<br/><br/>A população ela precisa da Zona Franca de Manaus, porque na Zona franca de Manaus, não é uma zona de exportação, é uma zona para o Brasil. Portanto ela tem um objetivo, ela evita o desmatamento, que é altamente lucravito. Derrubar arvores da natureza é muito lucrativo!<br/><br/>No meu xinélo da humildade eu gostaria muito de ver o Neymar e o Ganso. Por que eu acho que.... 11 entre 10 brasileiros gostariam. Você veja, eu já vi, parei de ver. Voltei a ver, e acho que o Neymar e o Ganso têm essa capacidade de fazer a gente olhar.<br/><br/>\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"nome\": \"Rise of the Tomb Raider\",\n" +
                "      \"urlImagem\": \"https://images-submarino.b2w.io/produtos/01/00/item/129125/0/129125074_1GG.png\",\n" +
                "      \"descricao\": \"Mussum Ipsum, cacilds vidis litro abertis. Atirei o pau no gatis, per gatis num morreus. Não sou faixa preta cumpadi, sou preto inteiris, inteiris. Praesent malesuada urna nisi, quis volutpat erat hendrerit non. Nam vulputate dapibus. Diuretics paradis num copo é motivis de denguis.<br/><br/>Copo furadis é disculpa de bebadis, arcu quam euismod magna. Casamentiss faiz malandris se pirulitá. Vehicula non. Ut sed ex eros. Vivamus sit amet nibh non tellus tristique interdum. in elementis mé pra quem é amistosis quis leo.<br/><br/>A ordem dos tratores não altera o pão duris Delegadis gente finis, bibendum egestas augue arcu ut est. Mé faiz elementum girarzis, nisi eros vermeio. Si u mundo tá muito paradis? Toma um mé que o mundo vai girarzis!\",\n" +
                "      \"precoDe\": 299,\n" +
                "      \"precoPor\": 119.99,\n" +
                "      \"categoria\": {\n" +
                "        \"id\": 1,\n" +
                "        \"descricao\": \"Games\",\n" +
                "        \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        return GsonBuilder().create().fromJson(mostSoldJson, ProductList::class.java)
    }

}