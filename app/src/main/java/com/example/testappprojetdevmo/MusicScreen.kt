package com.example.testappprojetdevmo

import android.graphics.Paint.Align
import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicScreen() {
    val navController = rememberNavController()
    // Données de test pour les partitions
    val partitions = listOf(
        Partition("Sonate au Clair de Lune", "Beethoven", "Classique", R.drawable.moonlight),
        Partition("Nocturne Op. 9 No. 2", "Chopin", "Romantique", R.drawable.nocturne),
        Partition("Gymnopédie No. 1", "Erik Satie", "Impressionniste", R.drawable.gymnopedie),
        Partition("Lettre à Élise", "Beethoven", "Classique", R.drawable.elise),
        Partition("Prélude en Do Majeur", "Bach", "Baroque", R.drawable.prelude),
        Partition("Rêverie", "Debussy", "Impressionniste", R.drawable.reverie),
        Partition("La Campanella", "Liszt", "Romantique", R.drawable.campanella),
        Partition("Arabesque No. 1", "Debussy", "Impressionniste", R.drawable.arabesque),
        Partition("Invention No. 1", "Bach", "Baroque", R.drawable.invention),
        Partition("Valse de l'Adieu", "Chopin", "Romantique", R.drawable.valse)
    )

    // État pour suivre l'élément de navigation sélectionné
    var selectedTab by remember { mutableIntStateOf(1) } // 1 correspond à la liste de partitions

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mes Partitions", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar (
                modifier  = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ){
                // Accordeur
                NavigationBarItem(
                    modifier = Modifier.weight(1f),
                    icon = { Icon(painter = painterResource(id = R.drawable.tuning_fork), contentDescription = "Accordeur", Modifier.size(70.dp)) },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                // Liste de partitions
                NavigationBarItem(
                    modifier = Modifier.weight(1f),
                    icon = { Icon(painter = painterResource(id = R.drawable.partitions), contentDescription = "Partitions", Modifier.size(70.dp)) },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                // Exercices
                NavigationBarItem(
                    modifier = Modifier.weight(1f),
                    icon = { Icon(painter = painterResource(id = R.drawable.notebook), contentDescription = "Exercices", Modifier.size(70.dp)) },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                // Enregistreur
                NavigationBarItem(
                    modifier = Modifier.weight(1f),
                    icon = { Icon(painter = painterResource(id = R.drawable.microphone), contentDescription = "Enregistreur", Modifier.size(70.dp)) },
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 }
                )
            }
        }
    ) { paddingValues ->
        // Contenu principal - seulement la liste de partitions est implémentée
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Affichage conditionnel en fonction de l'onglet sélectionné
            when (selectedTab) {
                0 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Écran Accordeur (Non implémenté)")
                }
                1 -> {
                    NavHost(
                        navController = navController,
                        startDestination = "partition_list"
                    ) {
                        composable("partition_list") {
                            PartitionList(partitions, navController)
                        }
                        composable("partition_detail/{partitionIndex}") {backStackEntry ->
                            val partitionIndex = backStackEntry.arguments?.getString("partitionIndex")?.toIntOrNull() ?: 0
                            if (partitionIndex < partitions.size){
                                PartitionDetailScreen(partitions[partitionIndex], navController)
                            }
                        }
                    }
                }
                2 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Écran Exercices (Non implémenté)")
                }
                3 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Écran Enregistreur (Non implémenté)")
                }
            }
        }
    }
}


@Composable
fun PartitionList(partitions: List<Partition>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(partitions.size) { index ->
            val partition = partitions[index]
            val onClick = { navController.navigate("partition_detail/$index") }
            PartitionItem(partition, onClick)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PartitionItem(partition: Partition, onClick : () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image de la partition avec l'identifiant de ressource spécifique
            Image(
                painter = painterResource(id = partition.imageResId),
                contentDescription = "Image de ${partition.title}",
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Informations sur la partition
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = partition.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Par ${partition.composer}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Genre: ${partition.genre}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// Classe pour représenter une partition
data class Partition(
    val title: String,
    val composer: String,
    val genre: String,
    val imageResId: Int // Identifiant de ressource pour l'image (R.drawable.xxx)
)

// Nouvel écran de détail de partition
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartitionDetailScreen(partition: Partition, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(partition.title) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image grande taille
            Image(
                painter = painterResource(id = partition.imageResId),
                contentDescription = "Image de ${partition.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

// Prévisualisation
@Preview(showBackground = true)
@Composable
fun PartitionListScreenPreview() {
    MaterialTheme {
        val partition = Partition("Sonate au Clair de Lune", "Beethoven", "Classique", R.drawable.moonlight)
        val navController = rememberNavController()
        PartitionDetailScreen(partition, navController)
    }
}