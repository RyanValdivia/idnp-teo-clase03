package com.example.teo03

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaCompra()
        }
    }
}

@Composable
fun PantallaCompra() {
    val context = LocalContext.current

    var cantidad by remember { mutableIntStateOf(2) }
    var canchita by remember { mutableStateOf(false) }
    var bebida by remember { mutableStateOf(false) }
    var cupon by remember { mutableStateOf(false) }

    val precioEntrada = 15
    val precioCanchita = 8
    val precioBebida = 5

    var subtotal = cantidad * precioEntrada
    if (canchita) {
        subtotal = subtotal + precioCanchita
    }
    if (bebida) {
        subtotal = subtotal + precioBebida
    }

    var descuento = 0
    if (cupon) {
        descuento = (subtotal * 10) / 100
    }

    val total = subtotal - descuento

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cinemachacas",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "COMPRA DE ENTRADAS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Cantidad de entradas", fontSize = 16.sp)
            Row {
                Button(onClick = {
                    if (cantidad > 1) {
                        cantidad = cantidad - 1
                    }
                }) {
                    Text(text = "-")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "  " + cantidad.toString() + "  ",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )

                Button(onClick = {
                    cantidad = cantidad + 1
                }) {
                    Text(text = "+")
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Extras", fontSize = 16.sp, fontWeight = FontWeight.Bold)

            Row {
                Checkbox(checked = canchita, onCheckedChange = { canchita = it })
                Text(text = "Canchita (+S/ 8)", modifier = Modifier.padding(top = 12.dp))
            }

            Row {
                Checkbox(checked = bebida, onCheckedChange = { bebida = it })
                Text(text = "Bebida (+S/ 5)", modifier = Modifier.padding(top = 12.dp))
            }

            Row {
                Checkbox(checked = cupon, onCheckedChange = { cupon = it })
                Text(text = "Aplicar cupon (10%)", modifier = Modifier.padding(top = 12.dp))
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Subtotal: S/ " + subtotal)
            Text(text = "Descuento: S/ " + descuento)
            Text(text = "Total: S/ " + total, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Compra realizada! Total S/ $total", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "COMPRAR")
            }
        }
    }
}
