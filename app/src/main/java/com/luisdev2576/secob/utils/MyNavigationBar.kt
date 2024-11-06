package com.luisdev2576.secob.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.luisdev2576.secob.navigation.DenunciasHomeDestination
import com.luisdev2576.secob.navigation.LideresHomeDestination
import com.luisdev2576.secob.navigation.NewDenunciaDestination
import com.luisdev2576.secob.navigation.NewLiderDestination

@Composable
fun MyNavigationBar(
    navHostController: NavHostController,
) {
    val currentDestination = navHostController
        .currentBackStackEntryAsState().value?.destination?.route

    val screensWithBottomBar = listOf(
        DenunciasHomeDestination.serializer().descriptor.serialName,
        LideresHomeDestination.serializer().descriptor.serialName
    )

    if(currentDestination in screensWithBottomBar){
        NavigationBar {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    MyNavItem(
                        icon = Icons.Default.Person,
                        label = "Lideres",
                        selected = currentDestination == LideresHomeDestination.serializer().descriptor.serialName,
                        onClick = { navHostController.navigate(LideresHomeDestination) }
                    )
                    Spacer(modifier = Modifier.width(32.dp))
                    MyNavItem(
                        icon = Icons.Default.Campaign,
                        label = "Denuncias",
                        selected = currentDestination == DenunciasHomeDestination.serializer().descriptor.serialName,
                        onClick = { navHostController.navigate(DenunciasHomeDestination) }
                    )
                }
                FloatingActionButton(
                    onClick = {
                        when (currentDestination) {
                            LideresHomeDestination.serializer().descriptor.serialName -> {
                                navHostController.navigate(NewLiderDestination)
                            }
                            DenunciasHomeDestination.serializer().descriptor.serialName -> {
                                navHostController.navigate(NewDenunciaDestination)
                            }
                        }
                    },
                ) {
                    Icon(Icons.Default.Add, "Home")
                }
            }
        }

    }

}

@Composable
fun MyNavItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Card(
            onClick = onClick,
            modifier = Modifier
                .width(60.dp),
            shape= CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.Transparent
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(vertical = 6.dp, horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Text(text = label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
    }
}