package com.bala.nytnews.ui.webView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.bala.nytnews.databinding.WebViewLayoutBinding

class WebViewFragment : Fragment() {

    private val viewBinding
        get() = _viewBinding!!
    private var _viewBinding: WebViewLayoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = WebViewLayoutBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.webView.settings.setJavaScriptEnabled(true)

        val url = arguments?.getString("url")

        viewBinding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        viewBinding.webView.loadUrl(url)
    }

}