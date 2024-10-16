package com.example.csti

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.csti.crud.obtenerSolicitudes
import com.example.csti.data.solicitud

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleReporteScreen(navController: NavController, solicitudId: Int, fromMisReportes: Boolean = true) {
    var solicitud by remember { mutableStateOf<solicitud?>(null) }

    // Obtener la solicitud específica de la base de datos
    LaunchedEffect(Unit) {
        val solicitudes = obtenerSolicitudes()
        solicitud = solicitudes.find { it.id_solicitud == solicitudId }
    }

    solicitud?.let { solicitud ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón de Volver
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Start),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c))
            ) {
                Text("< Volver", color = Color.White, fontSize = 10.sp)
            }

            // Logo en el centro
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Aula y No. Empleado
            Row(modifier = Modifier.fillMaxWidth()) {
                // Sección de Aula
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Aula",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = solicitud.aula,
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Black,
                            containerColor = Color(0xFFECECEC)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                // Sección de No. Empleado
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "No. Empleado",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = solicitud.id_profesor.toString(),
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                        colors = TextFieldDefaults.textFieldColors(
                            disabledTextColor = Color.Black,
                            containerColor = Color(0xFFECECEC)
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Descripción
            Text(
                text = "Descripción",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = solicitud.descripcion_problema,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Black,
                    containerColor = Color(0xFFECECEC),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Imagen debajo de la descripción
            Image(
                painter = painterResource(id = R.drawable.pc),
                contentDescription = "Imagen del problema",
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            // Solo si accede desde "Mis Reportes"
            if (fromMisReportes) {
                Spacer(modifier = Modifier.height(32.dp))
                // Recuadro de Feedback
                Text(
                    text = "Feedback:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                /**TextField(
                    value = feedback,
                    onValueChange = { feedback = it },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFECECEC)
                    ),
                    shape = RoundedCornerShape(20.dp)
                Spacer(modifier = Modifier.height(16.dp))
                )*/
                // Botón de Guardar Feedback
                Button(
                    onClick = {
                        // Lógica para guardar el feedback
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp)
                ) {
                    Text("Guardar Feedback", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botones debajo de la imagen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("MainScreen") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Finalizar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Lógica para actualizar */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tomar reporte", color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetalleReporteScreenPreview() {
    val dummySolicitud = solicitud(
        id_solicitud = 1,
        id_usuario_asignado = 2,
        fecha_hora_envio = "2024-10-01",
        id_profesor = 3,
        aula = "A101",
        estatus = "En progreso",
        descripcion_problema = "Problema con el proyector",
        tecnico_asignado = null
    )
    DetalleReporteScreen(navController = rememberNavController(), solicitudId = dummySolicitud.id_solicitud, fromMisReportes = true)
}
