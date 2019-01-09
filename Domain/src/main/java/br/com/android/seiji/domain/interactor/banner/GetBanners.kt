package br.com.android.seiji.domain.interactor.banner

import br.com.android.seiji.domain.executor.PostExecutionThread
import br.com.android.seiji.domain.interactor.SingleUseCase
import br.com.android.seiji.domain.model.Banner
import br.com.android.seiji.domain.repository.BannerRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetBanners @Inject constructor(
    private val bannerRepository: BannerRepository,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Banner>, Nothing?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Nothing?): Observable<List<Banner>> {
        return bannerRepository.getBanners()
    }
}