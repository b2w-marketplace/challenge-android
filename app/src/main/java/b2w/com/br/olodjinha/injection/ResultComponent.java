package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.queryresult.ResultActivity;
import b2w.com.br.olodjinha.queryresult.ResultPresenter;
import dagger.Component;

@Component(modules = ScreenFlowModule.class)
public interface ResultComponent {

    void inject(ResultActivity resultActivity);

    ResultPresenter presenter();

}
