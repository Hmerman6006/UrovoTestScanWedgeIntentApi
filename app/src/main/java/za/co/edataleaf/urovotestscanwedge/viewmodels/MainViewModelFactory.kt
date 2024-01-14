package za.co.edataleaf.urovotestscanwedge.viewmodels

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class MainViewModelFactory(
    private val savedStateRegistryOwner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(
    savedStateRegistryOwner,
    null) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return MainViewModel() as T
    }
}