package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.main.HomeFragment;
import b2w.com.br.olodjinha.main.HomePresenter;
import dagger.Component;

@Component(modules = {ScreenFlowModule.class})
public interface HomeComponent {

    void inject(HomeFragment activity);

    HomePresenter presenter();
}
