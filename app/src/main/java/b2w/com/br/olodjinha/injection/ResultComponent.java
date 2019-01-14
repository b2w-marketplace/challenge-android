package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.ui.queryresult.ResultActivity;
import b2w.com.br.olodjinha.ui.queryresult.ResultPresenter;
import dagger.Component;

@Component(modules = {ScreenFlowModule.class, NetworkModule.class})
public interface ResultComponent {

    void inject(ResultActivity resultActivity);

    ResultPresenter presenter();

}
