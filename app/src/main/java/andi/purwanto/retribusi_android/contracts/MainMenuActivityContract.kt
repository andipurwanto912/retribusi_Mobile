package andi.purwanto.retribusi_android.contracts

import andi.purwanto.retribusi_android.models.Post

interface MainMenuActivityContract {
    interface MainActivityView {
        fun showToast(message: String)
        fun attachToRecycler(posts: List<Post>)
    }

    interface MainMenuActivityPresenter {
        fun all(key: String)
        fun destroy()
    }
}