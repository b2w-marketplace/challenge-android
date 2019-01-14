package b2w.com.br.olodjinha.injection;

import b2w.com.br.olodjinha.data.api.DataFacade;
import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    DataFacade providesDataFacade() {
        return new DataFacade();
    }
}
