package br.com.rbueno.lodjinha.ui.home.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rbueno.lodjinha.R
import br.com.rbueno.lodjinha.model.Category
import com.google.gson.GsonBuilder

class CategoryFragment : Fragment() {

    private val recyclerView by lazy {view?.findViewById<RecyclerView>(R.id.recycler_category)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRecyclerView()
    }

    private fun configRecyclerView() {
        val adapter = CategoryAdapter(mockCategory())
        with(recyclerView) {
            //this?.layoutManager = LinearLayoutManager(this@CategoryFragment.context,)
            this?.adapter = adapter
        }
    }

    private fun mockCategory(): Category {
        val categoryJson = "{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"descricao\": \"Games\",\n" +
                "      \"urlImagem\": \"http://39ahd9aq5l9101brf3b8dq58.wpengine.netdna-cdn.com/wp-content/uploads/2013/06/3D-Gaming.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"descricao\": \"Livros\",\n" +
                "      \"urlImagem\": \"http://4.bp.blogspot.com/-6Bta1H9d22g/UJAIJbqcHhI/AAAAAAAAKi4/hvgjWrlFc64/s1600/resenha-missiologia.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"descricao\": \"Celulares\",\n" +
                "      \"urlImagem\": \"http://pt.seaicons.com/wp-content/uploads/2015/11/Mobile-Smartphone-icon.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"descricao\": \"Informática\",\n" +
                "      \"urlImagem\": \"http://portal.ifrn.edu.br/campus/ceara-mirim/noticias/ifrn-oferece-curso-de-informatica-basica-para-pais-dos-estudantes/image_preview\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"descricao\": \"Eletrodoméstico\",\n" +
                "      \"urlImagem\": \"http://classificados.folharegiao.com.br/files/classificados_categoria/photo/8/sm_4d5ed3beb0f31b61cb9a01e46ecd0cf9.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"descricao\": \"TVs\",\n" +
                "      \"urlImagem\": \"http://i.utdstc.com/icons/256/terrarium-tv-android.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"descricao\": \"Filmes e Séries\",\n" +
                "      \"urlImagem\": \"https://pbs.twimg.com/profile_images/801033586438733824/91Y_N91t_reasonably_small.jpg\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 8,\n" +
                "      \"descricao\": \"Móveis e Decorações\",\n" +
                "      \"urlImagem\": \"https://image.flaticon.com/icons/png/128/148/148188.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 9,\n" +
                "      \"descricao\": \"Moda, Beleza e Perfumaria\",\n" +
                "      \"urlImagem\": \"http://icon-icons.com/icons2/196/PNG/128/fashion_23852.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"descricao\": \"Papelaria\",\n" +
                "      \"urlImagem\": \"http://esen.pt/in/images/stories/skills_256.png\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        return GsonBuilder().create().fromJson(categoryJson, Category::class.java)
    }
}