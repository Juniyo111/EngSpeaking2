@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.engspeaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.engspeaking.ui.theme.EngSpeakingTheme

class Mainboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EngSpeakingTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mainboard") {
                    composable("mainboard") { MainScreen(navController = navController) }
                    composable("personal_info") { PersonalInfoScreen() }
                    composable("settings") { SettingsScreen() }
                    composable("search") { SearchScreen() }
                    composable("terms_of_service") { TermsOfServiceScreen() }
                    composable("privacy_policy") { PrivacyPolicyScreen() }
                    composable("hobbies_and_interests") { CourseDetailScreen("Hobbies and Interests") }
                    composable("presentation") { CourseDetailScreen("Presentation") }
                    composable("opic") { CourseDetailScreen("오픽 OPIC") }
                    composable("toeic") { CourseDetailScreen("토익 TOEIC SPEAKING") }
                    composable("business") { CourseDetailScreen("BUSINESS ENGLISH") }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("SPEAKING CONTENT 101") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("personal_info") }) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Personal Info")
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("search") }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                IconButton(onClick = { navController.navigate("mainboard") }) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                }
                Spacer(modifier = Modifier.weight(1f, true))
                TextButton(onClick = { navController.navigate("terms_of_service") }) {
                    Text("이용약관")
                }
                Spacer(modifier = Modifier.weight(1f, true))
                TextButton(onClick = { navController.navigate("privacy_policy") }) {
                    Text("개인정보처리방침")
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                EngSpeakingCourseSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                OPICSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                TOEICSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                BusinessEnglishSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                LatestLectureSection(navController)
            }
        }
    }
}

@Composable
fun EngSpeakingCourseSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6FA3EF))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "ENG Speaking Course",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.White
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { CourseItem(navController, "오픽", "opic", R.drawable.ic_launcher_foreground) }
            item { CourseItem(navController, "토익 스피킹", "toeic", R.drawable.ic_launcher_foreground) }
            item { CourseItem(navController, "영어회화", "conversation", R.drawable.ic_launcher_foreground) }
            item { CourseItem(navController, "Business English", "business", R.drawable.ic_launcher_foreground) }
        }
    }
}

@Composable
fun CourseItem(navController: NavHostController, title: String, route: String, imageRes: Int) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clickable { navController.navigate(route) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(text = title, fontSize = 16.sp, color = Color.Black)
    }
}

@Composable
fun OPICSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { navController.navigate("opic") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "오픽 OPIC",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Black
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { LevelCard(level = "Novice") }
            item { LevelCard(level = "Intermediate") }
            item { LevelCard(level = "Advanced") }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CourseCard(
                    navController = navController,
                    route = "opic_sample1",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 1",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
            item {
                CourseCard(
                    navController = navController,
                    route = "opic_sample2",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 2",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
        }
    }
}

@Composable
fun TOEICSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { navController.navigate("toeic") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "토익 TOEIC SPEAKING",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Black
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { LevelCard(level = "Beginner") }
            item { LevelCard(level = "Intermediate") }
            item { LevelCard(level = "Advanced") }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CourseCard(
                    navController = navController,
                    route = "toeic_sample1",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 1",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
            item {
                CourseCard(
                    navController = navController,
                    route = "toeic_sample2",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 2",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
        }
    }
}

@Composable
fun BusinessEnglishSection(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { navController.navigate("business") }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "BUSINESS ENGLISH",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Black
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { LevelCard(level = "Beginner") }
            item { LevelCard(level = "Intermediate") }
            item { LevelCard(level = "Advanced") }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CourseCard(
                    navController = navController,
                    route = "business_sample1",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 1",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
            item {
                CourseCard(
                    navController = navController,
                    route = "business_sample2",
                    image = painterResource(id = android.R.drawable.ic_menu_gallery),
                    title = "강사 2",
                    description = "샘플 | 주제: 일상 생활"
                )
            }
        }
    }
}

@Composable
fun LevelCard(level: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable { /* Add navigation or action */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(text = level, fontSize = 16.sp, color = Color.Black)
    }
}

@Composable
fun CourseCard(navController: NavHostController, route: String, image: Painter, title: String, description: String) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable { navController.navigate(route) }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = image,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = description, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        IconButton(
            onClick = { /* Play video */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play", tint = Color.Black)
        }
    }
}

@Composable
fun LatestLectureSection(navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("최신 강의 소개", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
            Spacer(modifier = Modifier.height(8.dp))
            LectureItem(navController, "Hobbies and Interests", "hobbies_and_interests")
            Spacer(modifier = Modifier.height(8.dp))
            LectureItem(navController, "Presentation", "presentation")
        }
    }
}

@Composable
fun LectureItem(navController: NavHostController, title: String, route: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(route) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface)
                Text("상세:", fontSize = 14.sp, color = Color.Gray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Today • 23 min", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
    }
}

@Composable
fun SubfolderScreen(title: String) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add content specific to each subfolder here
            BasicText("This is the content for $title")
        }
    }
}

@Composable
fun CourseDetailScreen(title: String) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add detailed content for the course here
            BasicText("Detailed content for $title")
        }
    }
}

@Composable
fun PersonalInfoScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Personal Information", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add personal information content here
            BasicText("This is the personal information screen.")
        }
    }
}

@Composable
fun SettingsScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Settings", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add settings content here
            BasicText("This is the settings screen.")
        }
    }
}

@Composable
fun SearchScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Search", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add search content here
            BasicText("This is the search screen.")
        }
    }
}

@Composable
fun TermsOfServiceScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Terms of Service", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add terms of service content here
            BasicText("This is the terms of service screen.")
        }
    }
}

@Composable
fun PrivacyPolicyScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Privacy Policy", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            // Add privacy policy content here
            BasicText("This is the privacy policy screen.")
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun MainboardPreview() {
    EngSpeakingTheme {
        val navController = rememberNavController()
        MainScreen(navController = navController)
    }
}
