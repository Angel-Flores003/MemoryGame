package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.UIKit.UIViewController

fun MainViewController() : UIViewController {//MainViewController = ComposeUIViewController { App() }
    Napier.base(DebugAntilog())
    return ComposeUIViewController { App() }
}