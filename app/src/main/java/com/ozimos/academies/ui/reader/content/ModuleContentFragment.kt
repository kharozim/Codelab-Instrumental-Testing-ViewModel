package com.ozimos.academies.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ozimos.academies.R
import com.ozimos.academies.data.ContentEntity
import com.ozimos.academies.databinding.FragmentModuleContentBinding


class ModuleContentFragment : Fragment() {

    private lateinit var binding: FragmentModuleContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModuleContentBinding.inflate(layoutInflater)

        if (activity != null) {
            val content =
                ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
            populateWebView(content)
        }


        return binding.root
    }

    private fun populateWebView(content: ContentEntity) {
        binding.webView.loadData(content.content ?: "", "text/html", "UTF-8")
    }


    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

}