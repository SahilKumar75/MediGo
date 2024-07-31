package com.example.musical

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.musical.navgraph.Navigation
import com.example.musical.navgraph.Screen
import com.example.musical.navgraph.screensInBottom
import com.example.musical.navgraph.screensInDrawer
import com.example.musical.presentation.accounts.AccountDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()
    val navController: NavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val dialogOpen = remember { mutableStateOf(false) }

    val currentScreen = viewModel.currentScreen.value
    val title = remember { mutableStateOf(currentScreen.title) }
    val username = viewModel.username.value

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen is Screen.BottomScreen) {
            Surface(
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                BottomNavigation(
                    modifier = Modifier.wrapContentSize(),
                    backgroundColor = Color(0xFF006eff),
                ) {
                    screensInBottom.forEach { item ->
                        val isSelected = currentRoute == item.bRoute
                        val tint = if (isSelected) Color.White else Color.Black
                        BottomNavigationItem(
                            selected = currentRoute == item.bRoute,
                            onClick = {
                                navController.navigate(item.bRoute) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                                viewModel.setCurrentScreen(item)
                                title.value = item.bTitle
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = item.bTitle,
                                    tint = tint
                                )
                            },
                            label = { Text(text = item.bTitle, color = tint) },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.Black
                        )
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (currentRoute !in listOf("chatRoute", "medChatScreen/{chatId}")) { // Conditionally show top bar
                TopAppBar(
                    title = {
                        if (currentScreen is Screen.BottomScreen.Home) {
                            Text(
                                text = "Hi, $username",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        } else {
                            Text(title.value, color = Color.Black)
                        }
                    },
                    backgroundColor = Color(0xFFF5F5F5),
                    elevation = 0.dp,
                    actions = {
                        if (currentRoute != "chatRoute") {
                            IconButton(
                                onClick = {
                                    navController.navigate("chatRoute")
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Chat, contentDescription = null, tint = Color.Black)
                            }
                        }
                    }
                )
            }
        },
        bottomBar = bottomBar,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp)) {
                items(screensInDrawer) { item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        if (item.dRoute == "add_account") {
                            dialogOpen.value = true
                        } else {
                            navController.navigate(item.dRoute)
                            viewModel.setCurrentScreen(item)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }
    ) {
        Navigation(navController = navController, viewModel = viewModel, pd = it)
        AccountDialog(dialogOpen = dialogOpen)
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5,
        )
    }
}
