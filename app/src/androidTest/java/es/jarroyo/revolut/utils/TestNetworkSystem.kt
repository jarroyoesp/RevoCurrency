package es.jarroyo.revolut.utils

open class TestNetworkSystem(): NetworkSystemAbstract() {

    companion object {
        var mIsNetworkAvailable = true

        fun setNetworkAvailable (isNetworkAvailable: Boolean){
            mIsNetworkAvailable = isNetworkAvailable
        }
    }


    override fun isNetworkAvailable(): Boolean {
        return mIsNetworkAvailable
    }
}