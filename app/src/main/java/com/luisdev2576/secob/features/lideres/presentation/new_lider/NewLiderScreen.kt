 package com.luisdev2576.secob.features.lideres.presentation.new_lider

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
 import androidx.compose.animation.core.tween

 @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
 @Composable
 fun NewLiderScreen(
     viewModel: NewLiderViewModel = hiltViewModel()
 ) {

     Scaffold(
         modifier = Modifier
             .background(MaterialTheme.colorScheme.surface)
             .fillMaxSize(),
         topBar = {
             CustomLinearProgressIndicator(progress = viewModel.progress.value, modifier = Modifier.fillMaxWidth().padding(10.dp))
         },
         content = {
             Column(
                 modifier = Modifier
                     .padding(it)
                     .background(MaterialTheme.colorScheme.surface)
                     .fillMaxSize()
                     .verticalScroll(rememberScrollState()),
                 horizontalAlignment = Alignment.CenterHorizontally
             ) {
                 LeaderName(
                     primerNombre = viewModel.primerNombre.value,
                     segundoNombre = viewModel.segundoNombre.value,
                     apellidoPaterno = viewModel.apellidoPaterno.value,
                     apellidoMaterno = viewModel.apellidoMaterno.value,
                     nombreIdentitario = viewModel.nombreIdentitario.value,
                     onPrimerNombreChange = viewModel::onPrimerNombreChange,
                     onSegundoNombreChange = viewModel::onSegundoNombreChange,
                     onApellidoPaternoChange = viewModel::onApellidoPaternoChange,
                     onApellidoMaternoChange = viewModel::onApellidoMaternoChange,
                     onNombreIdentitarioChange = viewModel::onNombreIdentitarioChange
                 )
             }
         }
     )
 }

 @Composable
 fun CustomLinearProgressIndicator(
     modifier: Modifier = Modifier,
     progress: Float,
     progressColor: Color = MaterialTheme.colorScheme.primary,
     backgroundColor: Color = MaterialTheme.colorScheme.surface,
     clipShape: Shape = RoundedCornerShape(16.dp),
     animationDuration: Int = 500 // Duración de la animación en milisegundos
 ) {
     // Animar el valor de progreso
     val animatedProgress by animateFloatAsState(
         targetValue = progress.coerceIn(0f, 1f), // Asegura que el valor esté entre 0 y 1
         animationSpec = tween(
             durationMillis = animationDuration
         )
     )

     Box(
         modifier = modifier
             .clip(clipShape)
             .background(backgroundColor)
             .height(8.dp)
     ) {
         Box(
             modifier = Modifier
                 .background(progressColor)
                 .fillMaxHeight()
                 .fillMaxWidth(animatedProgress)
         )
     }
 }

@Composable
fun LeaderName(
    primerNombre: String,
    segundoNombre: String,
    apellidoPaterno: String,
    apellidoMaterno: String,
    nombreIdentitario: String,
    onPrimerNombreChange: (String) -> Unit,
    onSegundoNombreChange: (String?) -> Unit,
    onApellidoPaternoChange: (String) -> Unit,
    onApellidoMaternoChange: (String?) -> Unit,
    onNombreIdentitarioChange: (String?) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Nombre completo")
            Spacer(modifier = Modifier.height(16.dp))
            MyTextField(
                value = primerNombre,
                onValueChange = onPrimerNombreChange,
                label = { Text("Primer nombre") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyTextField(
                value = segundoNombre,
                onValueChange = onSegundoNombreChange,
                label = { Text("Segundo nombre") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyTextField(
                value = apellidoPaterno,
                onValueChange = onApellidoPaternoChange,
                label = { Text("Apellido paterno") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyTextField(
                value = apellidoMaterno,
                onValueChange = onApellidoMaternoChange,
                label = { Text("Apellido materno") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
            )
            Spacer(modifier = Modifier.height(16.dp))
            MyTextField(
                value = nombreIdentitario,
                onValueChange = onNombreIdentitarioChange,
                label = { Text("Nombre indentitario") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default,
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                visualTransformation = VisualTransformation.None,
            )
        }
    }
}


@Composable
fun MyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        isError = isError,
        label = label,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        maxLines = maxLines,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        ),
        shape = RoundedCornerShape(16.dp)
    )
}