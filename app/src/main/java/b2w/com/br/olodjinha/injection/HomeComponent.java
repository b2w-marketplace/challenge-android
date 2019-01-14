package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.ui.home.HomeFragment;
import b2w.com.br.olodjinha.ui.home.HomePresenter;
import dagger.Component;

@Component(modules = {ScreenFlowModule.class, NetworkModule.class})
public interface HomeComponent {

    void inject(HomeFragment fragment);

    HomePresenter presenter();
}
