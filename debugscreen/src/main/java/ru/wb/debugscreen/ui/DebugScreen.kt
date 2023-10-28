package ru.wb.debugscreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.wb.debugscreen.R
import ru.wb.debugscreen.RequestDataBaseService
import ru.wb.debugscreen.domain.entities.NetworkInfo
import ru.wb.debugscreen.domain.entities.NetworkRequest
import ru.wb.debugscreen.utils.getColorMethodNetwork

@Composable
fun DebugScreen(
    overflowContent: @Composable (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val requests =
        RequestDataBaseService.getRequests().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            overflowContent?.invoke()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
//            scope.launch { NetworkDataBaseService.deleteAll() }
                },

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_share),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            IconButton(
                onClick = {
                    scope.launch { RequestDataBaseService.deleteAll() }
                },

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(defaultPadding)
        ) {
            requests.value.reversed().forEachIndexed { index, request ->
                item(key = index) {
                    RequestItem(request)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RequestItem(item: NetworkRequest) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val animate: Float by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(DEFAULT_ANIMATION_DURATION),
        label = "animationRotate"
    )

    Card(
        onClick = {
            isExpanded = !isExpanded
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = defaultPadding),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isSuccessful) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.errorContainer,
            contentColor = if (item.isSuccessful) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.error
        )
    ) {
        Column(
            modifier = Modifier
                .padding(defaultPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(defaultPadding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.spacedBy(defaultPadding)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Top),
                    verticalArrangement = Arrangement.spacedBy(defaultPadding)
                ) {
                    Text(
                        text = item.codeRequest.toString(),
                        color = if (item.isSuccessful) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1
                    )
                    Text(
                        text = item.method,
                        color = item.method.getColorMethodNetwork(),
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(defaultPadding)
                ) {
                    Text(
                        text = "${item.timeSendFormatted} (${item.timeReceived}) ms",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = item.getFullUrl(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_drop_down),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(animate)
                )
            }
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically(
                    animationSpec = tween(DEFAULT_ANIMATION_DURATION)
                ),
                exit = shrinkVertically(
                    animationSpec = tween(DEFAULT_ANIMATION_DURATION)
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(defaultPadding)
                ) {
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(defaultPadding),
                            verticalArrangement = Arrangement.spacedBy(defaultPadding)
                        ) {
                            Text(
                                text = "Query params:",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            if (item.queryParams.isEmpty()) {
                                TextEmpty(text = "Non queries")
                            } else {
                                item.queryParams.mapKeys {
                                    Text(text = "${it.key}=${it.value}")
                                }
                            }
                        }
                    }
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(defaultPadding),
                            verticalArrangement = Arrangement.spacedBy(defaultPadding)
                        ) {
                            Text(text = "Request", style = MaterialTheme.typography.bodyLarge)
                            ExpandItem(item = item.request)
                        }
                    }
                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(defaultPadding),
                            verticalArrangement = Arrangement.spacedBy(defaultPadding)
                        ) {
                            Text(text = "Response", style = MaterialTheme.typography.bodyLarge)
                            ExpandItem(item = item.response)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ExpandItem(item: NetworkInfo) {

    var selectIndex by rememberSaveable { mutableIntStateOf(0) }
    val heightTab = 36.dp
    val shapeIndicatorTab = MaterialTheme.shapes.extraLarge

    PrimaryTabRow(
        selectedTabIndex = selectIndex,
        modifier = Modifier
            .height(heightTab),
        divider = { }
    ) {
        Tab(
            selected = selectIndex == 0,
            onClick = { selectIndex = 0 },
            modifier = Modifier
                .clip(shapeIndicatorTab)
                .height(heightTab)
        ) {
            Text(text = "Headers")
        }
        Tab(
            selected = selectIndex == 1,
            onClick = { selectIndex = 1 },
            modifier = Modifier
                .clip(shapeIndicatorTab)
                .height(heightTab)
        ) {
            Text(text = "Body")
        }
    }
    TabContent(index = selectIndex, item = item)
}

@Composable
private fun TabContent(
    index: Int,
    item: NetworkInfo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp),
        verticalArrangement = Arrangement.spacedBy(defaultPadding)
    ) {
        when (index) {
            0 -> {
                if (item.header.isEmpty()) {
                    TextEmpty(text = "Non headers")
                } else {
                    item.header.mapKeys {
                        Text(text = "${it.key}: ${it.value}")
                    }
                }
            }

            1 -> {
                if (item.body.isNullOrEmpty()) {
                    TextEmpty(text = "EmptyBody")
                } else {
                    Text(text = item.body)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.TextEmpty(text: String) {
    Spacer(modifier = Modifier.weight(1f))
    Text(
        text = text,
        modifier = Modifier
            .align(Alignment.CenterHorizontally),
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.weight(1f))
}

private val defaultPadding = 8.dp
private const val DEFAULT_ANIMATION_DURATION = 300