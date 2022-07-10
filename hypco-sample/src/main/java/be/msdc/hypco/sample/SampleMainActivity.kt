package be.msdc.hypco.sample

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import be.msdc.hypco.api.HypCo
import be.msdc.hypco.api.ItemBuilder
import be.msdc.hypco.api.model.ItemType
import be.msdc.hypco.api.reporter.IHypCoReporter
import be.msdc.hypco.sample.ui.theme.HypContainerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class SampleMainActivity : ComponentActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    val hypco by lazy { HypCo.simple.apply {
        callback = IHypCoReporter.Callback { error ->
            runOnUiThread {
                Toast.makeText(this@SampleMainActivity, error.localizedMessage ?: error.toString(), Toast.LENGTH_LONG).show()
            }
        }
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HypCo.initialize(this.applicationContext)
        setContent {
            HypContainerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column() {
                        Button(
                            onClick = {
                                hypco.reportLayer2(ItemBuilder.preview(), "unknown")
                            }
                        ) {
                            Text(text = "Error Layer 2")
                        }
                        Button(
                            onClick = {
                                hypco.reportLayer1(ItemBuilder.preview())
                            }
                        ) {
                            Text(text = "Insert Layer 1")
                        }
                        Button(
                            onClick = {
                                hypco.reportLayer2(ItemBuilder.preview(), ItemBuilder.preview())
                            }
                        ) {
                            Text(text = "Insert Layer 2")
                        }
                        Button(
                            onClick = {
                                hypco.reportLayer3(ItemBuilder.jsonPreview(), ItemBuilder.preview().apply { content = null }, ItemBuilder.preview().apply { content = null })
                            }
                        ) {
                            Text(text = "Insert Layer 3")
                        }
                        Button(
                            onClick = {
                                val l3 = ItemBuilder.preview().apply {
                                    type = ItemType.SQUARE_CARD
                                }
                                val l2 = ItemBuilder.preview().apply {
                                    addChildren(l3)
                                    type = ItemType.PROGRESS_CARD
                                }
                                val l1 = ItemBuilder.preview().apply {
                                    addChildren(l2)
                                    type = ItemType.SIMPLE_CARD
                                }
                                val l22 = ItemBuilder.preview()
                                val l12 = ItemBuilder.preview()
                                hypco.reportLayer1(l22)
                                hypco.reportLayer1(l12)
                                hypco.reportLayer3(l3, l2, l1)
                                Handler().postDelayed({
                                    hypco.reportLayer3(l3, l22.id, l12.id)
                                    Toast.makeText(this@SampleMainActivity, "Update OK", Toast.LENGTH_LONG).show()
                                }, 3000)
                            }
                        ) {
                            Text(text = "Update Layer 3")
                        }
                        Button(
                            onClick = {
                                startActivity(HypCo.getLaunchIntent(this@SampleMainActivity))
                            }
                        ) {
                            Text(text = "HYPCO")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HypContainerTheme {
        Greeting("Android")
    }
}