package edu.diseno_apps.e_menulateral1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.diseno_apps.e_menulateral1.ui.theme.EMenuLateral1Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EMenuLateral1Theme {
                AppToolbar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopAppBar(
    onMenuClick: () -> Unit,
    onPerfilClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text("Mi Primera App")
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Abrir menú"
                )
            }
        },
        actions = {
            IconButton(onClick = onPerfilClick) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Abrir perfil"
                )
            }
        }
    )
}

@Composable
fun AppToolbar() {

    // PASO A: Crear controlador de navegación
    val navController = rememberNavController()

    // PASO B: Controlar el menú lateral
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    // PASO C: Obtener la pantalla activa
    val backStackEntry by
    navController.currentBackStackEntryAsState()

    val currentDestination = backStackEntry?.destination

    ModalNavigationDrawer(
        drawerState = drawerState,

        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp)
            ) {
                Text(
                    text = "Menú de Opciones",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(24.dp)
                )

                HorizontalDivider()

                Spacer(Modifier.height(8.dp))

                // PASO D: Opción Inicio
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = { Text("Inicio") },
                    selected =
                        currentDestination
                            ?.hasRoute<InicioDestination>() == true,
                    onClick = {
                        navController.navigate(
                            InicioDestination
                        ) {
                            popUpTo(
                                navController.graph
                                    .findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(
                        horizontal = 12.dp
                    )
                )

                // PASO E: Opción Configuración
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = null
                        )
                    },
                    label = { Text("Configuración") },
                    selected =
                        currentDestination
                            ?.hasRoute<ConfiguracionDestination>() == true,
                    onClick = {
                        navController.navigate(
                            ConfiguracionDestination
                        ) {
                            popUpTo(
                                navController.graph
                                    .findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(
                        horizontal = 12.dp
                    )
                )
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            Icons.Default.QuestionMark,
                            contentDescription = null
                        )
                    },
                    label = { Text("Acerca de") },
                    selected =
                    currentDestination
                        ?.hasRoute<AcercaDeDestination>() == true,
                    onClick = {
                        navController.navigate(
                            AcercaDeDestination
                        ) {
                            popUpTo(
                                navController.graph
                                    .findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                        scope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(
                        horizontal = 12.dp
                    )
                )
            }
        }
    ) {

        // PASO F: Construir la interfaz principal
        Scaffold(
            topBar = {
                MiTopAppBar(
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },
                    onPerfilClick = {
                        navController.navigate(
                            PerfilDestination
                        ) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) { paddingValues ->

            // PASO G: Mostrar las pantallas
            NavHost(
                navController = navController,
                startDestination = InicioDestination,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                composable<InicioDestination> {
                    InicioScreen()
                }

                composable<ConfiguracionDestination> {
                    ConfiguracionScreen()
                }

                composable<PerfilDestination> {
                    PerfilScreen()
                }
                composable<AcercaDeDestination> {
                    AcercaDeScreen()

                }
            }
        }
    }
}
