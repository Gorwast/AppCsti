package com.example.csti

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun MisReportesAdmin(navController: NavController) {
    var solicitudes by remember { mutableStateOf<List<solicitud>>(emptyList()) }

    LaunchedEffect(Unit) {
        solicitudes = obtenerSolicitudes()
    }

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

        // Título
        Text(
            text = "Mis Reportes en curso",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Aula",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(end = 20.dp),
                    text = "Descripción",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Listado de reportes
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(solicitudes) { reporte ->
                ReportItem(reporte) { selectedReporte ->
                    navController.navigate("detalle/${reporte.id_solicitud},${reporte.id_usuario_asignado},${reporte.fecha_hora_envio},${reporte.id_profesor},${reporte.aula},${reporte.estatus},${reporte.descripcion_problema}?fromMisReportes=true")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MisReportesAdminPreview() {
    MisReportesAdmin(navController = rememberNavController())
}
