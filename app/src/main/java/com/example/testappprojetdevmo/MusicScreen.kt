package com.example.testappprojetdevmo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartitionListScreen() {
    // Données de test pour les partitions
    val partitions = listOf(
        Partition("Sonate au Clair de Lune", "Beethoven", "Classique", 4),
        Partition("Nocturne Op. 9 No. 2", "Chopin", "Romantique", 3),
        Partition("Gymnopédie No. 1", "Erik Satie", "Impressionniste", 2),
        Partition("Lettre à Élise", "Beethoven", "Classique", 3),
        Partition("Prélude en Do Majeur", "Bach", "Baroque", 2),
        Partition("Rêverie", "Debussy", "Impressionniste", 4),
        Partition("La Campanella", "Liszt", "Romantique", 5),
        Partition("Arabesque No. 1", "Debussy", "Impressionniste", 3),
        Partition("Invention No. 1", "Bach", "Baroque", 2),
        Partition("Valse de l'Adieu", "Chopin", "Romantique", 4)
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
            NavigationBar {
                // Accordeur
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.tuning_fork), contentDescription = "Accordeur") },
                    label = { Text("Accordeur") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                // Liste de partitions
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.partitions), contentDescription = "Partitions") },
                    label = { Text("Partitions") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                // Exercices
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.notebook), contentDescription = "Exercices") },
                    label = { Text("Exercices") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
                // Enregistreur
                NavigationBarItem(
                    icon = { Icon(painter = painterResource(id = R.drawable.microphone), contentDescription = "Enregistreur") },
                    label = { Text("Enregistreur") },
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
                1 -> PartitionList(partitions)
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
fun PartitionList(partitions: List<Partition>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(partitions) { partition ->
            PartitionItem(partition)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun PartitionItem(partition: Partition) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Action au clic sur la partition */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icône ou image de la partition
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.partitions),
                    contentDescription = "Description",
                    modifier = Modifier.size(24.dp)
                )
            }

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
    val difficulty: Int // 1-5
)

// Prévisualisation
@Preview(showBackground = true)
@Composable
fun PartitionListScreenPreview() {
    MaterialTheme {
        PartitionListScreen()
    }
}