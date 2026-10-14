package com.example.semana9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.semana9.ui.theme.Semana9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana9Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ControlAforoApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ControlAforoApp(modifier: Modifier = Modifier) {
    // ----------------------------------------------------
    // 1. ESTADOS DE LA APLICACIÓN
    // ----------------------------------------------------
    // Contador de personas adentro (inicia en 0)
    var personasAdentro by remember { mutableIntStateOf(0) }

    // Nombre del último registrado (inicia en "Ninguno")
    var ultimoRegistrado by remember { mutableStateOf("Ninguno") }

    // Texto ingresado en el cuadro de texto (inicia vacío "")
    var nombreInput by remember { mutableStateOf("") }

    // ----------------------------------------------------
    // 2. ESTRUCTURA DE LA INTERFAZ (COLUMN)
    // ----------------------------------------------------
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- SECCIÓN 1: TÍTULO Y CONTADORES ---
        Text(
            text = "Control de Aforo",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Personas adentro: $personasAdentro",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Último registrado: $ultimoRegistrado",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))



        // --- SECCIÓN 2: FORMULARIO DE REGISTRO ---
        TextField(
            value = nombreInput,
            onValueChange = { nombreInput = it },
            label = { Text("Nombre de la persona") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (nombreInput.isNotBlank()) {
                    ultimoRegistrado = nombreInput  // Actualiza el último registrado
                    personasAdentro++             // Incrementa contador (+1)
                    nombreInput = ""                // Limpia el cuadro de texto
                }
            },

            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar e Ingresar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- SECCIÓN 3: FILA DE ACCIONES RÁPIDAS (ROW) ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botón "Salida (-1)"
            Button(
                onClick = {
                    // Restricción: Si ya está en 0, no reduce a números negativos
                    if (personasAdentro > 0) {
                        personasAdentro--
                    }


                },
                enabled = personasAdentro >0,

                modifier = Modifier.weight(1f)
            ) {
                Text("Salida (-1)")
            }

            // Botón "Reiniciar"
            Button(
                onClick = {
                    personasAdentro = 0
                    ultimoRegistrado = "Ninguno"
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Reiniciar")
            }
        }
    }
}
