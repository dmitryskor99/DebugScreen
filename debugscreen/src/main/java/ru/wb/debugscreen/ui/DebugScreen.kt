package ru.wb.debugscreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.wb.debugscreen.R
import ru.wb.debugscreen.data.NetworkDataBaseService
import ru.wb.debugscreen.domain.entities.NetworkRequest

@Composable
fun DebugScreen(
    overflowContent: @Composable (() -> Unit)? = null
) {
    val scope = rememberCoroutineScope()
    val requests =
        NetworkDataBaseService.getNetworkRequests()?.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            overflowContent?.invoke()
        }
        Button(
            onClick = {
                scope.launch { NetworkDataBaseService.deleteAll() }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = defaultPadding)
        ) {
            Text(text = "Clear all")
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            requests?.value?.reversed()?.forEach {
                item {
                    RequestItem(it)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RequestItem(item: NetworkRequest) {
    Card(
        onClick = {

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
        Row(
            modifier = Modifier.padding(defaultPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.spacedBy(defaultPadding)
        ) {
            Text(
                text = item.code.toString(),
                color = if (item.isSuccessful) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
            )
            Text(
                text = item.url,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
//            Spacer(modifier = Modifier.weight(1f))
//            Icon(
//                painter = painterResource(id = R.drawable.ic_arrow_drop_down),
//                contentDescription = null,
//            )
        }
    }
}

private val defaultPadding = 8.dp