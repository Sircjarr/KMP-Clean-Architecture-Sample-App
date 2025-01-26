package com.sircjarr.marvelrivalsherolookup.ui.screens.herodetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sircjarr.marvelrivalsherolookup.feature.herodetails.ui.HeroStat
import com.sircjarr.marvelrivalsherolookup.ui.res.ColorRes
import com.sircjarr.marvelrivalsherolookup.ui.res.pickRateColor
import com.sircjarr.marvelrivalsherolookup.ui.res.winRateColor

@Composable
fun HeroDetailsContent(
    modifier: Modifier,
    stats: List<HeroStat>,
    `class`: String,
    description: String,
    winRate: Float,
    pickRate: Float
) {

    Column(modifier = modifier) {
        Box(
            Modifier
                .background(ColorRes.gold)
                .padding(4.dp)
        ) {
            Text(`class`, color = Color.White, fontSize = 16.sp)
        }

        Spacer(Modifier.height(14.dp))

        Column(modifier = Modifier.fillMaxSize()) {

            Box(Modifier, contentAlignment = Alignment.TopCenter) {
                Text(text = description, style = MaterialTheme.typography.body1)
            }

            Spacer(Modifier.height(18.dp))

            Card(Modifier.fillMaxWidth()) {

                Column {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Base stats",
                        style = MaterialTheme.typography.h5
                    )
                    Divider(
                        Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .padding(start = 2.dp, end = 2.dp)
                    )

                    stats.forEach {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 8.dp,
                                    end = 8.dp,
                                    top = 4.dp,
                                    bottom = 4.dp
                                )
                        ) {
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(Modifier.weight(1f))
                            Text(
                                text = it.value,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$winRate%",
                            color = winRate.winRateColor,
                            fontSize = 32.sp
                        )
                        Text("Win rate", modifier = Modifier.padding(8.dp))
                    }
                }

                Spacer(Modifier.width(18.dp))

                Card(
                    Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$pickRate%",
                            color = pickRate.pickRateColor,
                            fontSize = 32.sp
                        )
                        Text("Pick rate", modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}