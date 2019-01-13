package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.main.MainActivity;
import dagger.Component;

@Component(modules = {ScreenFlowModule.class})
public interface MainComponent {

    void inject(MainActivity activity);
}
