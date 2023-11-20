package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

data class BusinessCardContactData(
    val icon: Painter,
    val text: String
)

data class BusinessCardData(
    val picture: Painter,
    val name: String,
    val jobTitle: String,
    val contactInformation: Array<BusinessCardContactData>
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardPreview()
                }
            }
        }
    }
}

@Composable
fun BusinessCard(businessCardData: BusinessCardData, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier
                .weight(2f),
        ){
            ProfileSection(businessCardData = businessCardData, modifier = modifier)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = modifier
                .weight(1f)
                .padding(bottom = 40.dp),
        ) {
            ContactSection(
                contactInformationData = businessCardData.contactInformation,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ProfileSection(businessCardData: BusinessCardData, modifier: Modifier) {
            Box(
                modifier = modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(90.dp))
                    .background(Color.Black)
            ) {
                Image(
                    painter = businessCardData.picture,
                    contentDescription = "portrait",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Text(
                text = businessCardData.name,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )
            Text(
                text = businessCardData.jobTitle,
                fontSize = 24.sp
            )
        }

@Composable
fun ContactSection(contactInformationData: Array<BusinessCardContactData>, modifier: Modifier) {
            contactInformationData.forEach { item ->
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                )
                {
                    Icon(
                        painter = item.icon,
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = modifier
                            .size(24.dp)
                    )
                    Text(
                        text = item.text,
                        fontSize = 18.sp,
                        modifier = modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }


@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    val businessCardData = BusinessCardData(
        picture = painterResource(R.drawable.portrait_picture),
        name = stringResource(R.string.name),
        jobTitle = stringResource(R.string.jobTitle),
        contactInformation = arrayOf(
            BusinessCardContactData(icon = painterResource(R.drawable.linkedin), text = stringResource(
                R.string.linkedin
            )
            ),
            BusinessCardContactData(icon = painterResource(R.drawable.github), text = stringResource(
                R.string.github
            )
            ),
            BusinessCardContactData(icon = painterResource(R.drawable.gmail), text = stringResource(
                R.string.gmail
            )
            ),
        )
    )
    BusinessCardTheme {
        BusinessCard(businessCardData = businessCardData)
    }
}