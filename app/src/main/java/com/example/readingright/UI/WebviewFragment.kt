package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.readingright.R
import kotlinx.android.synthetic.main.fragment_webview.*


class WebviewFragment : Fragment() {
    val args: WebviewFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_webview, container, false)
    }

    override fun onStart() {
        super.onStart()
        webview.apply {
            webViewClient = WebViewClient()
            loadUrl(args.url)
        }
    }
}