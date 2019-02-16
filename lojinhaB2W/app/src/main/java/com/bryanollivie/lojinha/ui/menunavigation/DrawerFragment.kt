/*
package com.tocalivros.android.ui.menunavigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.tocalivros.android.R
import com.tocalivros.android.ui.assinatura.AssinaturaFragment
import com.tocalivros.android.ui.biblioteca.MinhaBibliotecaFragment
import com.tocalivros.android.ui.categorias.CategoriasFragment
import com.tocalivros.android.ui.destaques.DestaquesFragment
import com.tocalivros.android.ui.player.PlayerActivity
import com.tocalivros.android.ui.voucher.VoucherActivity
import com.tocalivros.android.utils.SessionHelper
import com.zopim.android.sdk.api.ZopimChat
import com.zopim.android.sdk.prechat.PreChatForm
import com.zopim.android.sdk.prechat.ZopimChatActivity
import org.jetbrains.anko.support.v4.startActivity


class DrawerFragment : Fragment() {

    private var views: View? = null
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null
    lateinit var session: SessionHelper

    lateinit var nav_menu_biblioteca: LinearLayout
    lateinit var nav_menu_assinaturas: LinearLayout
    lateinit var nav_menu_destaques: LinearLayout
    lateinit var nav_menu_categorias: LinearLayout
    lateinit var nav_menu_consultor: LinearLayout
    lateinit var nav_menu_notificacoes: LinearLayout
    lateinit var nav_menu_voucher: LinearLayout
    lateinit var nav_menu_configuracoes: LinearLayout
    lateinit var nav_menu_suporte: LinearLayout
    lateinit var nav_menu_player: LinearLayout
    lateinit var nav_menu_logout: LinearLayout
    lateinit var nav_image_user: ImageView
    //lateinit var no_connection_reconect: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        session = SessionHelper(activity!!.applicationContext)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        views = inflater.inflate(R.layout.fragment_drawer, container, false)
        nav_menu_biblioteca = views!!.findViewById(R.id.nav_menu_biblioteca)
        nav_menu_assinaturas = views!!.findViewById(R.id.nav_menu_assinaturas)
        nav_menu_destaques = views!!.findViewById(R.id.nav_menu_destaques)
        nav_menu_categorias = views!!.findViewById(R.id.nav_menu_categorias)
        nav_menu_consultor = views!!.findViewById(R.id.nav_menu_consultor)
        nav_menu_notificacoes = views!!.findViewById(R.id.nav_menu_notificacoes)
        nav_menu_voucher = views!!.findViewById(R.id.nav_menu_voucher)
        nav_menu_configuracoes = views!!.findViewById(R.id.nav_menu_configuracoes)
        nav_menu_suporte = views!!.findViewById(R.id.nav_menu_suporte)
        nav_menu_player = views!!.findViewById(R.id.nav_menu_player)
        nav_menu_logout = views!!.findViewById(R.id.nav_menu_logout)
        nav_image_user = views!!.findViewById(R.id.nav_image_user)
        //no_connection_reconect = views!!.findViewById(R.id.no_connection_reconect)


        nav_image_user.setOnClickListener {
            //  startActivity<UserPerfilActivity>()
        }

        nav_menu_biblioteca.setOnClickListener {

            removeAllFragment(MinhaBibliotecaFragment(), "Minha Biblioteca")
            mDrawerLayout!!.closeDrawer(containerView!!)

        }

        nav_menu_assinaturas.setOnClickListener {
            removeAllFragment(AssinaturaFragment(), "Assinaturas")
            mDrawerLayout!!.closeDrawer(containerView!!)
        }

        nav_menu_destaques.setOnClickListener {
            removeAllFragment(DestaquesFragment(), "Destaques")
            mDrawerLayout!!.closeDrawer(containerView!!)
        }

        nav_menu_categorias.setOnClickListener {
            removeAllFragment(CategoriasFragment(), "Categorias")
            mDrawerLayout!!.closeDrawer(containerView!!)
        }

        nav_menu_consultor.setOnClickListener {
            openChat(activity!!.application)

        }

        nav_menu_notificacoes.setOnClickListener {
        }

        nav_menu_voucher.setOnClickListener {
            startActivity<VoucherActivity>()
        }

        nav_menu_configuracoes.setOnClickListener {
            //   startActivity<SettingsActivity>()

        }

        nav_menu_suporte.setOnClickListener {

            openChat(activity!!.application)
        }

        nav_menu_player.setOnClickListener {

            startActivity<PlayerActivity>()
        }

        nav_menu_logout.setOnClickListener {
            session.logoutUser()
            mDrawerLayout!!.closeDrawer(containerView!!)
        }

        openFragment(0)
        return views
    }


    private fun openChat(context: Context) {
        val build = PreChatForm.Builder()
                .name(PreChatForm.Field.OPTIONAL)
                .email(PreChatForm.Field.OPTIONAL)
                .phoneNumber(PreChatForm.Field.OPTIONAL)
                .message(PreChatForm.Field.OPTIONAL)
                .build()

        val department = ZopimChat.SessionConfig()
                .preChatForm(build)
                .department("The date")

        ZopimChatActivity.startActivity(context, department)
    }


    private fun openFragment(position: Int) {

        when (position) {
            0 -> removeAllFragment(DestaquesFragment(), "Biblioteca")
            //0 -> removeAllFragment(InicioActivity(), "Destaques")
            else -> {
            }
        }
    }

    fun removeAllFragment(replaceFragment: Fragment, tag: String) {

        val manager = activity!!.supportFragmentManager
        val ft = manager.beginTransaction()
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        ft.replace(R.id.container_body, replaceFragment)
        ft.commitAllowingStateLoss()
    }

    fun setUpDrawer(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {

        containerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        mDrawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
            }
        }

        mDrawerLayout!!.setDrawerListener(mDrawerToggle)
        mDrawerLayout!!.post { mDrawerToggle!!.syncState() }
        mDrawerToggle!!.getDrawerArrowDrawable().setColor(resources.getColor(R.color.colorBackgroundButtonBuy))

    }

    private class LoggedInClickListener internal constructor(private val onClickListener: View.OnClickListener) : View.OnClickListener {

        override fun onClick(view: View) {
            */
/* if (ZendeskConfig.INSTANCE.isInitialized()) {
                 onClickListener.onClick(view)
             } else {
                 //showDialog(view.context)
             }*//*

        }

        */
/*private fun showDialog(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.dialog_auth_title)
                    .setPositiveButton(R.string.dialog_auth_positive_btn, { dialog, id -> CreateProfileActivity.start(context) })
                    .setNegativeButton(R.string.dialog_auth_negative_btn, null)
            builder.create().show()
        }*//*

    }
}*/
