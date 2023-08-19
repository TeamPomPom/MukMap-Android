package com.example.presentation.ui.screens.common

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "1. small font",
    group = "font scales",
    fontScale = 0.5f,
    showBackground = true
)
@Preview(
    name = "2. large font",
    group = "font scales",
    fontScale = 1.5f,
    showBackground = true
)
annotation class FontScalePreviews

@Preview(
    name = "1. google pixel 7",
    group = "devices",
    device = "id:pixel_7",
    showBackground = true,
)
@Preview(
    name = "2. phone",
    group = "devices",
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showBackground = true,
)
@Preview(
    name = "3. small width device",
    group = "devices",
    device = "spec:id=reference_phone,shape=Normal,width=320,height=891,unit=dp,dpi=420",
    showBackground = true,
)
@Preview(
    name = "4. foldable",
    group = "devices",
    device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480",
    showBackground = true,
)
@Preview(
    name = "5. tablet",
    group = "devices",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=420",
    showBackground = true,
)
annotation class MultipleDevicePreviews

@MultipleDevicePreviews
@FontScalePreviews
annotation class MukMapPreviews