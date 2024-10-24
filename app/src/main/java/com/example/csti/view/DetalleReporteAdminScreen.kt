package com.example.csti.view

/**
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleReporteAdminScreen(navController: NavController, solicitudId: String) {
    var solicitud by remember { mutableStateOf<Solicitud?>(null) }
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(solicitudId) {
        val docRef = db.collection("Solicitud").document(solicitudId)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    solicitud = document.toObject(solicitud::class.java)
                }
            }
            .addOnFailureListener { exception ->
                // Manejar el error aquí (ej. mostrar un mensaje al usuario)
            }
    }

    solicitud?.let { solicitudValue ->
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
            // Sección de Aula y No. Solicitud
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
                        value = solicitudValue.aula,
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
                // Sección de No. Solicitud
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "No. Solicitud",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = solicitudValue.id_solicitud,
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
                value = solicitudValue.descripcion_problema,
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
            Spacer(modifier = Modifier.height(32.dp))
            // Botones adicionales para el administrador
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Lógica para asignar reporte */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Finalizar", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Lógica para tomar reporte */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Tomar Reporte", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* Lógica para asignar como urgente */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Asignar Reporte", color = Color.White)
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            // Botones debajo de la imagen
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("MainScreenAdmin") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1f3b3c)),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Marcar como urgente", color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}
*/