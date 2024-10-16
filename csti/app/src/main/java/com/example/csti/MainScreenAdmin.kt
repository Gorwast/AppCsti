package com.example.csti

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.csti.crud.obtenerSolicitudes
import com.example.csti.data.solicitud

@Composable
fun MainScreenAdmin(navController: NavController) {
    var solicitudes by remember { mutableStateOf<List<solicitud>>(emptyList()) }

    LaunchedEffect(Unit) {
        solicitudes = obtenerSolicitudes()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .size(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Botones en un solo renglón
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Lógica para ir a Reportes */ },
                modifier = Modifier.weight(1f),
                colors = buttonColors(containerColor = Color(0xFF637373)),
                border = BorderStroke(1.dp, Color(0xFF1f3b3c)),
                elevation = ButtonDefaults.elevatedButtonElevation(6.dp),
            ) {
                Text("Reportes", color = Color.White, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.width(6.dp))  // Espacio entre botones
            Button(
                onClick = { navController.navigate("misReportes") },
                modifier = Modifier.weight(1f),
                colors = buttonColors(containerColor = Color(0xFF1f3b3c))
            ) {
                Text("Mis Proyectos", color = Color.White, fontSize = 10.sp)
            }
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                onClick = { navController.navigate("login") },  // Cerrar sesión
                modifier = Modifier.weight(1f),
                colors = buttonColors(containerColor = Color(0xFF1f3b3c))
            ) {
                Text("Cerrar Sesión", color = Color.White, fontSize = 10.sp)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {}, // Lógica Gestión de Usuarios
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 30.dp, end = 3.dp),
                colors = buttonColors(containerColor = Color(0xFF1f3b3c))
            ) {
                Text("Gestión de Usuarios", color = Color.White, fontSize = 10.sp)
            }
            Button(
                onClick = {}, // Lógica Reporte del Sistema
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 3.dp, end = 30.dp),
                colors = buttonColors(containerColor = Color(0xFF1f3b3c))
            ) {
                Text("Reporte del sistema", color = Color.White, fontSize = 10.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Encabezado de la lista
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
            // Listado de reportes con diseño en recuadro y esquinas redondeadas
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(solicitudes) { reporte ->
                    AdminReportItem(reporte) { selectedReporte ->
                        // Navegar pasando el ID del reporte
                        navController.navigate("DetalleReporteAdminScreen/${selectedReporte.id_solicitud},${selectedReporte.id_usuario_asignado},${selectedReporte.fecha_hora_envio},${selectedReporte.id_profesor},${selectedReporte.aula},${selectedReporte.estatus},${selectedReporte.descripcion_problema}")
                    }
                }
            }
        }
    }
}

@Composable
fun AdminReportItem(solicitud: solicitud, onClick: (solicitud) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF2EEEE)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(solicitud) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = solicitud.aula,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = solicitud.descripcion_problema,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenAdminPreview() {
    MainScreenAdmin(navController = rememberNavController())
}
