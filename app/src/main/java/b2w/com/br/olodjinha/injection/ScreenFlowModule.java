package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.screenflow.ChangeActivityHandler;
import b2w.com.br.olodjinha.util.screenflow.FragmentFlowManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ScreenFlowModule {

    @Provides
    ChangeActivityHandler providesChangeActivityHandler(){
        return new ChangeActivityHandler();
    }

    @Provides
    FragmentFlowManager providesFragmentFlowManager() {
        return new FragmentFlowManager();
    }
}
